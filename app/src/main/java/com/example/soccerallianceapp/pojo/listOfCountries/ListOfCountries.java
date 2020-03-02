
package com.example.soccerallianceapp.pojo.listOfCountries;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfCountries {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("Countries")
    @Expose
    private List<String> countries = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "ListOfCountries{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", countries=" + countries +
                '}';
    }
}
