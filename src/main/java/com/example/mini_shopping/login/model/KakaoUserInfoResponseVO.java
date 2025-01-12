package com.example.mini_shopping.login.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class KakaoUserInfoResponseVO {

    private long id;

    private boolean has_signed_up;

    private LocalDateTime synched_at;

    private LocalDateTime connected_at;

    private KakaoProperties properties;

    private KakaoAccount kakao_account;

    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    public static class KakaoProperties {
        private String nickname;

    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    public static class KakaoAccount {
        private boolean profile_nickname_needs_agreement;
        private KakaoProfile profile;

        private boolean has_email;
        private boolean email_needs_agreement;
        private boolean is_email_valid;
        private boolean is_email_verified;

        private String email;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    public static class KakaoProfile {
        private String nickname;
        private String profile_image_url;
    }


}
