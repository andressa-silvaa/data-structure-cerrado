package backend;

import java.io.InputStream;


public class Records {
    private String fid;
    private String state;
    private String pathRow;
    private String mainClass;
    private String nameClass;
    private int julianDay;
    private String imageDate;
    private int year;
    private String areaKm;
    private String satellite;
    private String sensor;
    private String uuid;
    private String imagem;
    private InputStream blob;

    public Records(String fid, String state, String pathRow, String mainClass, String nameClass, int julianDay,
                    String imageDate, int year, String areaKm, String satellite, String sensor, String uuid, String imagem, InputStream blob) {
        this.fid = fid;
        this.state = state;
        this.pathRow = pathRow;
        this.mainClass = mainClass;
        this.nameClass = nameClass;
        this.julianDay = julianDay;
        this.imageDate = imageDate;
        this.year = year;
        this.areaKm = areaKm;
        this.satellite = satellite;
        this.sensor = sensor;
        this.uuid = uuid;
        this.imagem = imagem;
        this.blob = blob;
    }

    public String getFid() {
        return fid;
    }

    public String getState() {
        return state;
    }

    public String getPathRow() {
        return pathRow;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public int getJulianDay() {
        return julianDay;
    }

    public String getImageDate() {
        return imageDate;
    }

    public int getYear() {
        return year;
    }

    public String getAreaKm() {
        return areaKm;
    }

    public String getSatellite() {
        return satellite;
    }

    public String getSensor() {
        return sensor;
    }

    public String getUuid() {
        return uuid;
    }
    public String getImagem() {
        return imagem;
    }
    public InputStream getBlob() {
        return blob;
    }
}