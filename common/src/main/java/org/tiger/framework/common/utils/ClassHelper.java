package org.tiger.framework.common.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description
 *
 * @author lihj
 * @version v1.0
 *
 * @2013-8-5
 *
 */
public class ClassHelper {
	
	private static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	
	private static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	
	public static void setDateFormat(String patten){
		DATE_FORMAT_DEFAULT = patten;
	}

	public static String getStringValue(Field field, Object obj){
		String value;
		try {
			field.setAccessible(true);
			if(int.class == field.getType()){
				value = Integer.toString(field.getInt(obj));
			} else if(short.class == field.getType()){
				value = Short.toString(field.getShort(obj));
			} else if(long.class == field.getType()){
				value = Long.toString(field.getLong(obj));
			} else if(float.class == field.getType()){
				value = Float.toString(field.getFloat(obj));
			} else if(double.class == field.getType()){
				value = Double.toString(field.getDouble(obj));
			} else if(boolean.class == field.getType()){
				value = Boolean.toString(field.getBoolean(obj));
			} else if(char.class == field.getType()){
				value = Character.toString(field.getChar(obj));
			} else if(byte.class == field.getType()){
				value = Byte.toString(field.getByte(obj));
			} else if(String.class == field.getType()){
				value = (String)field.get(obj);
			} else if(Date.class == field.getType()){
				if(field.get(obj) != null){
					SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_DEFAULT);
					value = sf.format((Date)field.get(obj));
				} else {
					value = null;
				}
			} else {
				value = field.get(obj) != null? field.get(obj).toString():null;
			}
			field.setAccessible(false);
		} catch (IllegalArgumentException e) {
			field.setAccessible(false);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			field.setAccessible(false);
			throw new RuntimeException(e);
		}
		return value;
	}
	
	public static String getStringValue(Object obj){
		String value;
		try {
			if(int.class == obj.getClass() || Integer.class == obj.getClass()){
				value = Integer.toString((Integer)obj);
			} else if(long.class == obj.getClass() || Long.class == obj.getClass()){
				value = Long.toString((Long)obj);
			} else if(float.class == obj.getClass() || Float.class == obj.getClass()){
				BigDecimal b = new BigDecimal((Float)obj);
				b.setScale(0, BigDecimal.ROUND_HALF_UP);
				value = b.toString();
			} else if(double.class == obj.getClass() || Double.class == obj.getClass()){
				BigDecimal b = new BigDecimal((Double)obj);
				b.setScale(0, BigDecimal.ROUND_HALF_UP);
				value = b.toString();
			} else {
				value = String.valueOf(obj);
			}
			
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} 
		return value;
	}
	
	public static void setStringValue(Field field, Object obj, Object value){
		if(value.getClass() == field.getType()){
			try {
				field.setAccessible(true);
				field.set(obj, value);
				field.setAccessible(false);
			} catch (IllegalArgumentException e) {
				field.setAccessible(false);
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				field.setAccessible(false);
				throw new RuntimeException(e);
			}
		} else {
			setStringValue(field, obj, getStringValue(value));
		}
	}
	
	public static void setStringValue(Field field, Object obj, String value){
		try {
			field.setAccessible(true);
			if(int.class == field.getType()){
				try {
					field.setInt(obj, Integer.parseInt(value));
				} catch (NumberFormatException e) {
					try {
						field.setInt(obj, (int)Double.parseDouble(value));
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			} else if(Integer.class == field.getType()){
				try {
					field.set(obj, new Integer(value));
				} catch (NumberFormatException e) {
					try {
						field.set(obj, (int)Double.parseDouble(value));
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			} else if(short.class == field.getType()){
				try {
					field.setShort(obj, Short.parseShort(value));
				} catch (NumberFormatException e) {
					try {
						field.setShort(obj, (short)Double.parseDouble(value));
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			} else if(Short.class == field.getType()){
				try {
					field.set(obj, Short.parseShort(value));
				} catch (NumberFormatException e) {
					try {
						field.set(obj, (short)Double.parseDouble(value));
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			} else if(long.class == field.getType()){
				try {
					field.setLong(obj, Long.parseLong(value));
				} catch (NumberFormatException e) {
					try {
						field.setLong(obj, (long)Double.parseDouble(value));
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			} else if(Long.class == field.getType()){
				try {
					field.set(obj, Long.parseLong(value));
				} catch (NumberFormatException e) {
					try {
						field.set(obj, (long)Double.parseDouble(value));
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
				}
			} else if(float.class == field.getType()){
				field.setFloat(obj, Float.parseFloat(value));
			} else if(Float.class == field.getType()){
				field.set(obj, Float.parseFloat(value));
			} else if(double.class == field.getType()){
				field.setDouble(obj, Double.parseDouble(value));
			} else if(Double.class == field.getType()){
				field.set(obj, Double.parseDouble(value));
			} else if(boolean.class == field.getType()){
				field.setBoolean(obj, Boolean.parseBoolean(value));
			} else if(Boolean.class == field.getType()){
				field.set(obj, Boolean.parseBoolean(value));
			} else if(char.class == field.getType()){
				field.setChar(obj, value.toCharArray()[0]);
			} else if(Character.class == field.getType()){
				field.set(obj, value.toCharArray()[0]);
			} else if(byte.class == field.getType()){
				field.setByte(obj, Byte.parseByte(value));
			} else if(Byte.class == field.getType()){
				field.set(obj, Byte.parseByte(value));
			} else if(String.class == field.getType()){
				field.set(obj, value);
			} else if(Date.class == field.getType()){
				SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_DEFAULT);
				try {
					field.set(obj, sf.parse(value));
				} catch (ParseException e) {
					sf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
					try {
						field.set(obj, sf.parse(value));
					} catch (ParseException e1) {
						throw new RuntimeException(e);
					}
				}
			} else {
				field.set(obj, value);
			}
			field.setAccessible(false);
		} catch (IllegalArgumentException e) {
			field.setAccessible(false);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			field.setAccessible(false);
			throw new RuntimeException(e);
		}
	}
}
