package com.dal.hrm_management.models.profile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class ProfileResponse {

        @SerializedName("result_code")
        @Expose
        private String resultCode;
        @SerializedName("result_message")
        @Expose
        private String resultMessage;
        @SerializedName("data")
        @Expose
        private Data data;

        /**
         * No args constructor for use in serialization
         *
         */
        public ProfileResponse() {
        }

        /**
         *
         * @param data
         * @param resultCode
         * @param resultMessage
         */
        public ProfileResponse(String resultCode, String resultMessage, Data data) {
            super();
            this.resultCode = resultCode;
            this.resultMessage = resultMessage;
            this.data = data;
        }

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMessage() {
            return resultMessage;
        }

        public void setResultMessage(String resultMessage) {
            this.resultMessage = resultMessage;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
}
