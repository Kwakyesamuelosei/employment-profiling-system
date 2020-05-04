package io.turntabl.employementprofilingsystem.Models;

import java.util.Date;

public class RequestTO {
    private int request_id;
    private String requester_email;
    private int requester_id;
    private Date request_start_date;
    private Date request_report_date;
    private int request_status_id;
    private String from;
    private String requester_name;
    private String req_status;

    public RequestTO() {
    }

    public RequestTO(int request_id, String requester_email, int requester_id, Date request_start_date, Date request_report_date, int request_status_id, String from, String requester_name, String req_status) {
        this.request_id = request_id;
        this.requester_id = requester_id;
        this.request_start_date = request_start_date;
        this.request_report_date = request_report_date;
        this.request_status_id = request_status_id;
        this.from = from;
        this.requester_name = requester_name;
        this.req_status = req_status;
        this.requester_email = requester_email;
    }
    public String getRequester_email() { return requester_email; }

    public void setRequester_email(String requester_email) { this.requester_email = requester_email; }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(int requester_id) {
        this.requester_id = requester_id;
    }

    public Date getRequest_start_date() {
        return request_start_date;
    }

    public void setRequest_start_date(Date request_start_date) {
        this.request_start_date = request_start_date;
    }

    public Date getRequest_report_date() {
        return request_report_date;
    }

    public void setRequest_report_date(Date request_report_date) {
        this.request_report_date = request_report_date;
    }

    public int getRequest_status_id() {
        return request_status_id;
    }

    public void setRequest_status_id(int request_status_id) {
        this.request_status_id = request_status_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRequester_name() {
        return requester_name;
    }

    public String getReq_status() {
        return req_status;
    }

    public void setReq_status(String req_status) {
        this.req_status = req_status;
    }

        @Override
    public String toString() {
        return "RequestTO{" +
                "request_id=" + request_id +
                ", requester_email='" + requester_email + '\'' +
                ", requester_id=" + requester_id +
                ", request_start_date=" + request_start_date +
                ", request_report_date=" + request_report_date +
                ", request_status_id=" + request_status_id +
                ", from='" + from + '\'' +
                ", requester_email='" + requester_name + '\'' +
                ", req_status='" + req_status + '\'' +
                '}';
    }
}

