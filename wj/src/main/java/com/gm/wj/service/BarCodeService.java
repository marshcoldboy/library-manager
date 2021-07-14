package com.gm.wj.service;

import com.gm.wj.dao.BarCodeDAO;
import com.gm.wj.entity.BarCode;
import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarcodeScanner;
import com.spire.barcode.BarcodeSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;


@Service
@Transactional
public class BarCodeService {

    @Autowired
    BarCodeDAO barCodeDAO;

    public void generateBarCode(String data) throws IOException {
        BarCode barCode=new BarCode(data);

        BarCodeGenerator barCodeGenerator =new BarCodeGenerator(barCode.getBarcodeSettings());
        BufferedImage bufferedImage = barCodeGenerator.generateImage();
        ImageIO.write(bufferedImage,"png",new File("CODE129.png"));
        System.out.println("Down");
    }

    public String readBarCode() throws Exception {

        String data= BarcodeScanner.scanOne(".\\Code129.png");
        System.out.println(data);
        return data;
    }
}
