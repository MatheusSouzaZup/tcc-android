package br.com.bluedot.redevalor.ui.clipboard;

import br.com.bluedot.redevalor.ui.LoadingView;

public class ClipBoardContract {

    interface Presenter {
        void getTasks();
    }

    interface View extends LoadingView {
        void showTasks();
    }
}
