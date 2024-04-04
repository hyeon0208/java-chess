package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.game.Winner;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.dto.ChessGameResponse;
import chess.dto.GameResultResponse;
import chess.dto.PieceResponse;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private final GameDao gameDao;
    private final PieceDao pieceDao;

    public ChessGameService(final GameDao gameDao, final PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public Long saveGame(final ChessGame chessGame) {
        return Transaction.start(connection -> {
            Long gameId = gameDao.save(chessGame, connection);
            List<PieceResponse> pieceResponses = getPieceResponses(chessGame.getBoard());
            for (PieceResponse pieceResponse : pieceResponses) {
                pieceDao.save(pieceResponse, gameId, connection);
            }
            return gameId;
        });
    }

    public void loadGame(final ChessGame chessGame, final Long gameId) {
        Transaction.start(connection -> {
            ChessGameResponse chessGameResponse = gameDao.findById(gameId, connection);
            chessGame.load(chessGameResponse);
            return null;
        });
    }

    public List<Long> findAllGame() {
        return Transaction.start(connection -> {
            List<Long> gameIds = gameDao.findIdAll(connection);
            return gameIds;
        });
    }

    public List<PieceResponse> getPieceResponses(final Board board) {
        Map<Square, Piece> pieces = board.getPieces();
        return pieces.entrySet().stream()
                .map(entry -> PieceResponse.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    public GameResultResponse getGameResultResponse(final ChessGame chessGame) {
        Score score = chessGame.createScore();
        double whiteScore = score.calculateTotalScoreBy(Color.WHITE);
        double blackScore = score.calculateTotalScoreBy(Color.BLACK);
        Winner winner = Winner.of(whiteScore, blackScore);
        return new GameResultResponse(whiteScore, blackScore, winner);
    }
}
