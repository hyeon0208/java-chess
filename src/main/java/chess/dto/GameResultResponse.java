package chess.dto;

import chess.domain.game.Winner;

public record GameResultResponse(double whiteScore, double blackScore, Winner winner) {
}
