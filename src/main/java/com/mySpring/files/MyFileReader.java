package com.mySpring.files;

import com.mySpring.entity.SupplierProduct;
import com.mySpring.entity.SupplierProductPKID;
import com.mySpring.service.*;
import com.opencsv.CSVReader;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public class MyFileReader {
	SupplierProductService sp=null;
	
	public MyFileReader(SupplierProductService sp) {
		this.sp=sp;
	}
	
	public void readDir(File folder) {
        for (File fileEntry : folder.listFiles()) {
        	//if there is inner folder then recursively call readDir
        	if(fileEntry.isDirectory()) readDir(fileEntry);
        	else {
	        	//get supplierId first from fileName
	        	String[] supplier=fileEntry.getName().split("\\.");
	        	String supplierId=supplier[0];
	        	System.out.println(supplierId);
	        	//getData and addData with appropriate file type
	            if(fileEntry.isFile() && fileEntry.getName().endsWith(".txt")) addTxtData(fileEntry, supplierId);
	            if(fileEntry.isFile() && fileEntry.getName().endsWith(".csv")) addCsvData(fileEntry, supplierId);
	            if(fileEntry.isFile() && fileEntry.getName().endsWith(".xlsx") || fileEntry.isFile() && fileEntry.getName().endsWith(".xls")) addXlsData(fileEntry, supplierId);	
        	}
            
        }
    }
	
	//get product index and quantity index
	public int[] getindexes(String[] s) {
		List<String> word=new ArrayList<>();
		word.add("inventory");
		word.add("quantity");
		int[] indexes=new int[2];
		for(int i=0; i<s.length; i++) {
			s[i]=s[i].toLowerCase();
			//System.out.println(s[i]);
        	if(s[i].equals("product")) {
        		indexes[0]=i;
        	}
        	if(word.contains(s[i])) {
        		indexes[1]=i;
        	}
        }
		return indexes;
	}
	
	//addData to the database
	public void addData(String[] temp, String supplierId, int productindex, int quantityindex) {
    	double quantity=-1;
    	String productName=temp[productindex];
        quantity=Double.parseDouble(temp[quantityindex]);
        SupplierProductPKID pk=new SupplierProductPKID(supplierId, productName);
        SupplierProduct newsp=new SupplierProduct(pk, quantity);
        if(this.sp.getSupplierProductById(pk)!=null) {
        	this.sp.updateSupplierProduct(newsp);
        }else {
        	this.sp.saveSupplierProduct(newsp);
        }
	}
	
	//deal with txt
	public void addTxtData(File fileEntry, String supplierId) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileEntry))) {
			//get product index and quantity index first with first row
			String line=br.readLine();
            line.toLowerCase();
            line=line.replaceAll("\\s",","); 
            String[] temp=line.split(",");
            int productindex=-1;
            int quantityindex=-1;
            int[] index=getindexes(temp);
            productindex=index[0];
            quantityindex=index[1];
            
            //Iterate and add appropriate data to the database
            while ((line = br.readLine()) != null) {   	
                line=line.replaceAll("\\s",",");  
                String[] temp1=line.split(",");
                addData(temp1, supplierId, productindex, quantityindex);
            }
        }catch (IOException e) {
            e.printStackTrace();  
        }
	}
	
	//deal with csv
	public void addCsvData(File fileEntry, String supplierId) {
		try (CSVReader reader = new CSVReader(new FileReader(fileEntry))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
            //get indexes of product and quantity
            int productindex=-1;
            int quantityindex=-1;
            int[] index=getindexes(r.get(0));
            productindex=index[0];
            quantityindex=index[1];
            for(int i=1; i<r.size(); i++) {
            	addData(r.get(i), supplierId, productindex, quantityindex);
            }
        }catch (Exception e) {  
        	e.printStackTrace();  
    	}  
	}
	
	//deal with xls
	public void addXlsData(File fileEntry, String supplierId) {
		try{
    		XSSFWorkbook workbook=new XSSFWorkbook(fileEntry);
    		XSSFSheet sheet=workbook.getSheetAt(0);
    		int productindex=-1;
            int quantityindex=-1;
    		
    		for(int r=0; r<=sheet.getLastRowNum(); r++) {
    			XSSFRow row=sheet.getRow(r);
    			String[] cellvalues=new String[sheet.getRow(1).getLastCellNum()];
    			for(int c=0; c<sheet.getRow(1).getLastCellNum(); c++) {
    				XSSFCell cell=row.getCell(c);
    				switch(cell.getCellType()){
        				case STRING: cellvalues[c]=cell.getStringCellValue(); break;
        				case NUMERIC: cellvalues[c]=String.valueOf(cell.getNumericCellValue()); break;
        				case BOOLEAN: cellvalues[c]=String.valueOf(cell.getBooleanCellValue()); break;
    				}
    			}
    			if(r==0) {
    				int[] index=getindexes(cellvalues);
    				productindex=index[0];
    	            quantityindex=index[1];
    			}else {
    				addData(cellvalues, supplierId, productindex, quantityindex);
    			}
    		}
        }catch (Exception e) {  
        	e.printStackTrace();  
    	}  
	}
}
