package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarCodeType;
import com.spire.barcode.BarcodeSettings;
import lombok.Data;
import lombok.ToString;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class BarCode {

    private BarcodeSettings barcodeSettings;

//    private BarCodeType barCodeType;
//    private String data;
//    private String data2D;
//    private float lineWidth;
//    private int imageHeight;
//    private int imageWidth;
//    private Boolean border;
//    private int borderWidth;
//    private Color borderColor;
//    private Color backColor;

    public BarCode() { }

    public BarCode( String data_data2D) {

        barcodeSettings=new BarcodeSettings();
        barcodeSettings.setType(BarCodeType.Code_128);//指定条码类型
        barcodeSettings.setData(data_data2D);//设置条码数据
        barcodeSettings.setData2D(data_data2D);//设置条码显示数据
        barcodeSettings.setShowTextOnBottom(true);//设置数据文本显示在条码底部
        barcodeSettings.setX(0.8f);//设置黑白条宽度
        barcodeSettings.setImageHeight(50);        //设置生成的条码图片高度
        barcodeSettings.setImageWidth(70);//设置生成的条码图片宽度
        barcodeSettings.hasBorder(true);//设置边框可见
        barcodeSettings.setBorderColor(new Color(135,206,250));//设置条码边框颜色
        barcodeSettings.setBorderWidth(1);//设置条码边框宽度
        barcodeSettings.setBackColor(new Color(240,255,255));//设置条码背景色
    }
}
