package org.jetbrains.java.decompiler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.java.decompiler.main.collectors.BytecodeMappingTracer;
import org.jetbrains.java.decompiler.modules.decompiler.exps.Exprent;
import org.jetbrains.java.decompiler.struct.StructClass;

/**
 * Created by Jonathan Beaudoin on 7/19/2015.
 */
public final class FieldOrder {

	private static Map<String, List<String>> fieldOrders = new HashMap<String, List<String>>();

	public static void bindTo(StructClass clazz, Exprent exprent) {
		List<String> strings = fieldOrders.getOrDefault(clazz.qualifiedName, new ArrayList<String>());

		String buff = exprent.toJava(0, new BytecodeMappingTracer()).toString();
		buff = buff.substring(buff.indexOf(".") + 1, buff.length());
		strings.add(buff);

		fieldOrders.put(clazz.qualifiedName, strings);
	}

	public static List<String> forClass(StructClass clazz) {
		return fieldOrders.get(clazz.qualifiedName);
	}

}
