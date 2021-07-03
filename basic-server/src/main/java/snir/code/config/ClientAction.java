package snir.code.config;

public enum ClientAction {
    SIGNUP_EMAIL_CONFIRM(1),
    API_EMAIL_CONFIRM(2),
    LABEL_EMAIL_CONFIRM(3),//External wallet
    CHANGE_PASSWORD_CONFIRM(4),
    CHANGE_EMAIL_CONFIRM(5);



    int val;
    ClientAction(int i) {
        this.val = i;
    }

    public int val(){
        return val;
    }
}
