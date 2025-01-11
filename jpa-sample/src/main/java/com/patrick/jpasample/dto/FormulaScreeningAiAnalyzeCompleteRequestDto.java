package com.patrick.jpasample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FormulaScreeningAiAnalyzeCompleteRequestDto {
    private MandatoryLLMRequestDto mandatory;

    public FormulaScreeningAiAnalyzeCompleteRequestDto() {

    }

    public FormulaScreeningAiAnalyzeCompleteRequestDto(
            MandatoryLLMRequestDto mandatory) {
        this.mandatory = mandatory;
    }

    public MandatoryLLMRequestDto getMandatory() {
        return mandatory;
    }

    public static class MandatoryLLMRequestDto {

        private CertificateDetail llmResponse;

        public MandatoryLLMRequestDto() {

        }

        public MandatoryLLMRequestDto(
                CertificateDetail llmResponse) {
            this.llmResponse = llmResponse;
        }

        public CertificateDetail getLlmResponse() {
            return llmResponse;
        }
    }

    public static class CertificateDetail {

        @JsonProperty("eu")
        private Map<String, Object> cpnp;

        @JsonProperty("us")
        private Map<String, Object> mocra;

        @JsonProperty("ko")
        private Map<String, Object> qcqa;

        public CertificateDetail() {

        }

        public CertificateDetail(Map<String, Object> cpnp,
                Map<String, Object> mocra, Map<String, Object> qcqa) {
            this.cpnp = cpnp;
            this.mocra = mocra;
            this.qcqa = qcqa;
        }

        public Map<String, Object> getCpnp() {
            return cpnp;
        }
    }


}
