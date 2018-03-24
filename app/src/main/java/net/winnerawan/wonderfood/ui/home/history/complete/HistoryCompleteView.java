package net.winnerawan.wonderfood.ui.home.history.complete;

import net.winnerawan.wonderfood.data.db.model.History;
import net.winnerawan.wonderfood.ui.base.MvpView;

import java.util.List;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public interface HistoryCompleteView extends MvpView {
    void showHistory(List<History> history);
}
