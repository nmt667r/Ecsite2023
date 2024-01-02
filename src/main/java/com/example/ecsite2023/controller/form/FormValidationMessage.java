package com.example.ecsite2023.controller.form;

public class FormValidationMessage {

    protected static class UserForm{
        protected final static Long ACCOUNT_MAX_LENGTH = 10L;
        protected final static Long ACCOUNT_MIN_LENGTH = 4L;
        protected final static Long NAME_MAX_LENGTH = 20L;
        protected final static Long NAME_MIN_LENGTH = 2L;
        protected final static Long PASSWORD_MAX_LENGTH = 20L;
        protected final static Long PASSWORD_MIN_LENGTH = 8L;
        protected final static Long CONFIRM_PASSWORD_MAX_LENGTH = 20L;
        protected final static Long CONFIRM_PASSWORD_MIN_LENGTH = 8L;

        protected final static String ACCOUNT_BLANK = "アカウント名を入力してください";
        protected final static String NAME_BLANK = "ユーザー名を入力してください";
        protected final static String PASSWORD_BLANK = "パスワードを入力してください";
        protected final static String CONFIRM_PASSWORD_BLANK = "アカウント名を入力してください";

        protected final static String ACCOUNT_LENGTH_OVER = "アカウント名は10文字以下で入力してください";
        protected final static String NAME_LENGTH_OVER = "名前は20文字以下で入力してください";
        protected final static String PASSWORD_LENGTH_OVER = "パスワードは20文字以下で入力してください";
        protected final static String CONFIRM_PASSWORD_LENGTH_OVER = "パスワード確認は20文字以下で入力してください";
        protected final static String ACCOUNT_LENGTH_UNDER = "アカウント名は10文字以下で入力してください";
        protected final static String NAME_LENGTH_UNDER = "ユーザー名は20文字以下で入力してください";
        protected final static String PASSWORD_LENGTH_UNDER = "パスワードは20文字以下で入力してください";
        protected final static String CONFIRM_PASSWORD_LENGTH_UNDER = "確認用パスワードは20文字以下で入力してください";

    }

}
