package br.com.bluedot.redevalor.custom.status;

import br.com.bluedot.redevalor.R;

public enum Status {
    START(R.drawable.bg_status_green, R.string.start),
    PROGRESS(R.drawable.bg_status_yellow, R.string.in_progress),
    FINISH(R.drawable.bg_status_red, R.string.finish);


    private int color;
    private int status;

    Status(int color, int status) {
        this.color = color;
        this.status = status;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
