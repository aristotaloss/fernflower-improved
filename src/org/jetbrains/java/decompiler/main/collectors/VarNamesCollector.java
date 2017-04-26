/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.java.decompiler.main.collectors;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.java.decompiler.code.CodeConstants;
import org.jetbrains.java.decompiler.struct.gen.VarType;

public class VarNamesCollector {

	private final Set<String> usedNames = new HashSet<String>();

	public VarNamesCollector() {
	}

	public VarNamesCollector(Set<String> setNames) {
		usedNames.addAll(setNames);
	}

	public void addName(String value) {
		usedNames.add(value);
	}

	public String getFreeName(int index) {
		return getFreeName("var" + index);
	}

	public String getFreeName(String proposition) {
		while (usedNames.contains(proposition)) {
			proposition += "x";
		}
		usedNames.add(proposition);
		return proposition;
	}

	public String getFreeName_(String proposition) {
		int ptr = 1;
		if (usedNames.contains(proposition)) {
			while (usedNames.contains(proposition + "_" + ptr)) {
				ptr++;
			}
			proposition += "_" + ptr;
		}
		usedNames.add(proposition);
		return proposition;
	}
	
	public String varTypeToName(VarType varType, int index) {
		String name = "var";
		String arrayname = "arr";
		switch (varType.type) {
		case CodeConstants.TYPE_INT: name = "i"; arrayname = "ints"; break;
		case CodeConstants.TYPE_BOOLEAN: name = "bool"; arrayname = "bools"; break;
		case CodeConstants.TYPE_BYTE: name = "b"; arrayname = "bytes"; break;
		case CodeConstants.TYPE_FLOAT: name = "f"; arrayname = "floats"; break;
		case CodeConstants.TYPE_SHORT: name = "s"; arrayname = "shorts"; break;
		case CodeConstants.TYPE_DOUBLE: name = "d"; arrayname = "doubles"; break;
		case CodeConstants.TYPE_LONG: name = "long"; arrayname = "longs"; break;
		case CodeConstants.TYPE_OBJECT:
			if (varType == VarType.VARTYPE_STRING) {
				name = "str"; arrayname = "strings";
			} else if (varType == VarType.VARTYPE_OBJECT) {
				name = "obj"; arrayname = "objects";
			} else {
				name = varType.value.toLowerCase();

				if (name.contains("/")) {
					name = name.substring(name.lastIndexOf('/') + 1);
				}
			}
			return getFreeName_(varType.arrayDim > 0 ? arrayname : name);
		}
		return getFreeName(varType.arrayDim > 0 ? arrayname : (name + "_" + index));
	}

}
