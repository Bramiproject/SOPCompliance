package com.forestry.sopcompliance.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.forestry.sopcompliance.di.ActivityScope;
import com.forestry.sopcompliance.utils.TimeUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fimansya on 5/15/2017.
 */

@ActivityScope
public class Hotspot extends BaseObservable implements Serializable{

    private int _id;
    private int ID;
    private Double XCoordinate;
    private Double YCoordinate;
    private String HTDate;
    private String source;
    private String Agency;
    private String AdaApi;
    private String Remarks;
    private String companyCode;
    private String company;
    private String districtCode;
    private String district;
    private String KEYID;
    private int RowNo;
    private String VerificationDate;
    private int IsFireExist;
    private String Desa;
    private String Kecamatan;
    private String Kabupaten;
    private String HTTime;
    private String Hotspot;
    private String LocationId;
    private String Bu;
    private String FireSource;
    private String FullName;
    private String Vegetation;
    private String FireAreaSize;
    private String FireTreatment;
    private String Latitude;
    private String Longitude;
    private String FireDescStatus;
    private String images;

    private ArrayList<String> photos = new ArrayList<>();
    private String verifiedStatus;
    private int imageListLength;

    private String timeDiff;


    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public int getImageListLength() {
        return imageListLength;
    }

    public void setImageListLength(int imageListLength) {
        this.imageListLength = imageListLength;
    }

    public String getTimeDiff() {
        timeDiff = TimeUtils.hotspotTimeDiff(HTTime);
        return timeDiff;
    }

    public void setTimeDiff(String timeDiff) {
        this.timeDiff = timeDiff;
    }


    @Bindable
    public boolean isVerifiedStatus() {
        return verifiedStatus.equals("true");
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public Hotspot(){
    }

    public Hotspot(int _id, String company, String district, String bu, double lng, double lat, String verification) {
        this._id = _id;
        this.company = company;
        this.district = district;
        this.Bu = bu;
        this.XCoordinate = lng;
        this.YCoordinate = lat;
        this.verifiedStatus = verification;
    }

    public Hotspot(int _id, int ID, double lng, double lat, String HTDate, String source, String Agency, String AdaApi,
                   String Remarks, String companyCode, String company, String districtCode, String district, String KEYID,
                   int RowNo, String VerificationDate, int IsFireExist, String Desa, String Kecamatan, String Kabupaten,
                   String HTTime, String Hotspot, String LocationId, String Bu, String FireSource, String fullname,
                   String vegetation, String FireAreaSize, String FireTreatment, String FireDescSattus,
                   String images, String verification) {
        this._id = _id;
        this.ID = ID;
        this.XCoordinate = lng;
        this.YCoordinate = lat;
        this.HTDate = HTDate;
        this.source = source;
        this.Agency = Agency;
        this.AdaApi = AdaApi;
        this.Remarks = Remarks;
        this.companyCode = companyCode;
        this.company = company;
        this.districtCode = districtCode;
        this.district = district;
        this.KEYID = KEYID;
        this.RowNo = RowNo;
        this.VerificationDate = VerificationDate;
        this.IsFireExist = IsFireExist;
        this.Desa = Desa;
        this.Kecamatan = Kecamatan;
        this.Kabupaten = Kabupaten;
        this.HTTime = HTTime;
        this.Hotspot = Hotspot;
        this.LocationId = LocationId;
        this.Bu = Bu;
        this.FireSource = FireSource;
        this.FullName = fullname;
        this.Vegetation = vegetation;
        this.FireAreaSize = FireAreaSize;
        this.FireTreatment = FireTreatment;
        this.FireDescStatus = FireDescSattus;
        this.images = images;
        this.verifiedStatus = verification;

    }


    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Bindable
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    @Bindable
    public String getHTTime() {
        return HTTime;
    }

    @Bindable
    public String getDistrictCode() {
        return districtCode;
    }

    @Bindable
    public String getCompanyCode() {
        return companyCode;
    }

    @Bindable
    public String getBu() {
        return Bu;
    }

    public void setBu(String bu) {
        this.Bu = bu;
    }


    @Bindable
    public Double getLongitude() {
        return XCoordinate;
    }

    public void setLongitude(Double longitude) {
        this.XCoordinate = longitude;
    }

    @Bindable
    public Double getLatitude() {
        return YCoordinate;
    }

    public void setLatitude(Double latitude) {
        this.YCoordinate = latitude;
    }


    @Bindable
    public String getAdaApi() {
        return AdaApi;
    }

    public void setAdaApi(String adaApi) {
        AdaApi = adaApi;
    }

    @Bindable
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Bindable
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Bindable
    public String getHTDate() {
        return HTDate;
    }

    public void setHTDate(String HTDate) {
        this.HTDate = HTDate;
    }


    @Bindable
    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    @Bindable
    public String getFullname() {
        return FullName;
    }

    public void setFullname(String fullname) {
        FullName = fullname;
    }


    @Bindable
    public String getSource() {
        return source;
    }

    @Bindable
    public String getAgency() {
        return Agency;
    }

    @Bindable
    public String getKEYID() {
        return KEYID;
    }

    @Bindable
    public int getRowNo() {
        return RowNo;
    }

    @Bindable
    public String getVerificationDate() {
        return VerificationDate;
    }

    @Bindable
    public int getIsFireExist() {
        return IsFireExist;
    }

    @Bindable
    public String getDesa() {
        return Desa;
    }

    @Bindable
    public String getKecamatan() {
        return Kecamatan;
    }

    @Bindable
    public String getKabupaten() {
        return Kabupaten;
    }


    @Bindable
    public String getHotspot() {
        return Hotspot;
    }

    @Bindable
    public String getLocationId() {
        return LocationId;
    }

    @Bindable
    public String getFireSource() {
        return FireSource;
    }

    @Bindable
    public String getVegetation() {
        return Vegetation;
    }

    @Bindable
    public String getFireAreaSize() {
        return FireAreaSize;
    }

    @Bindable
    public String getFireTreatment() {
        return FireTreatment;
    }

    @Bindable
    public String getFireDescStatus() {
        return FireDescStatus;
    }


}
