package com.hw3.CommonPackage;

public class Request {
    private RequestType requestType;
    private FieldType fieldType;
    private Citation citation;

    public Request(RequestType requestType, FieldType fieldType, Citation citation) {
        this.requestType = requestType;
        this.fieldType = fieldType;
        this.citation = citation;
    }

    public Request() {}

    // Getters and Setters
    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public Citation getCitation() {
        return citation;
    }

    public void setCitation(Citation citation) {
        this.citation = citation;
    }
}
