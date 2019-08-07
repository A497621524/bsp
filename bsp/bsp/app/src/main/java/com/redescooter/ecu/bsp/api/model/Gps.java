package com.redescooter.ecu.bsp.api.model;

public class Gps {
    private String GPGSV;
    private String GPGLL;
    private String GPRMC;
    private String GPGGA;
    private String GPGSA;

    public String getGPGSV() {
        return GPGSV;
    }

    public void setGPGSV(String GPGSV) {
        this.GPGSV = GPGSV;
    }

    public String getGPGLL() {
        return GPGLL;
    }

    public void setGPGLL(String GPGLL) {
        this.GPGLL = GPGLL;
    }

    public String getGPRMC() {
        return GPRMC;
    }

    public void setGPRMC(String GPRMC) {
        this.GPRMC = GPRMC;
    }

    public String getGPGGA() {
        return GPGGA;
    }

    public void setGPGGA(String GPGGA) {
        this.GPGGA = GPGGA;
    }

    public String getGPGSA() {
        return GPGSA;
    }

    public void setGPGSA(String GPGSA) {
        this.GPGSA = GPGSA;
    }

    @Override
    public String toString() {
        return "Gps{" +
                "GPGSV='" + GPGSV + '\'' +
                ", GPGLL='" + GPGLL + '\'' +
                ", GPRMC='" + GPRMC + '\'' +
                ", GPGGA='" + GPGGA + '\'' +
                ", GPGSA='" + GPGSA + '\'' +
                '}';
    }
}
