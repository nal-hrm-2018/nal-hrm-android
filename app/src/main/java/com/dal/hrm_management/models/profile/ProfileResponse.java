package com.dal.hrm_management.models.profile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class ProfileResponse {

        @SerializedName("result_code")
        @Expose
        private int resultCode;
        @SerializedName("result_message")
        @Expose
        private String resultMessage;
        @SerializedName("data")
        @Expose
        private Profile profile;

        /**
         * No args constructor for use in serialization
         *
         */
        public ProfileResponse() {
        }

        /**
         *
         * @param profile
         * @param resultCode
         * @param resultMessage
         */
        public ProfileResponse(int resultCode, String resultMessage, Profile profile) {
            super();
            this.resultCode = resultCode;
            this.resultMessage = resultMessage;
            this.profile = profile;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMessage() {
            return resultMessage;
        }

        public void setResultMessage(String resultMessage) {
            this.resultMessage = resultMessage;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }
}
