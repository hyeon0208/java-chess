package chess.service;

import chess.dao.GameRepository;
import chess.dao.PieceRepository;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.view.dto.ChessGameResponse;
import chess.view.dto.PieceResponse;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private final GameRepository gameRepository;
    private final PieceRepository pieceRepository;

    public ChessGameService(final GameRepository gameRepository, final PieceRepository pieceRepository) {
        this.gameRepository = gameRepository;
        this.pieceRepository = pieceRepository;
    }

    public Long saveGame(final ChessGame chessGame) {
        Long gameId = gameRepository.save(chessGame);
        List<PieceResponse> pieceResponses = getPieceResponses(chessGame.getBoard());
        for (PieceResponse pieceResponse : pieceResponses) {
            pieceRepository.save(pieceResponse, gameId);
        }
        return gameId;
    }

    public void loadGame(final ChessGame chessGame, final Long gameId) {
        ChessGameResponse chessGameResponse = gameRepository.findById(gameId);
        chessGame.load(chessGameResponse);
    }

    public List<Long> findAllGame() {
        return gameRepository.findIdAll();
    }

    public List<PieceResponse> getPieceResponses(final Board board) {
        Map<Square, Piece> pieces = board.getPieces();
        return pieces.entrySet().stream()
                .map(entry -> new PieceResponse(
                        entry.getKey().getFileIndex(),
                        entry.getKey().getRankIndex(),
                        entry.getValue().type(),
                        entry.getValue().color())
                ).toList();
    }
}
