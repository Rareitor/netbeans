/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.javafx2.editor.completion.model;

import javax.lang.model.element.TypeElement;
import org.netbeans.api.java.source.ElementHandle;

/**
 *
 * @author sdedic
 */
public final class FxReference extends FxObjectBase {
    private String        targetId;
    private FxNewInstance    target;
    
    FxReference(String targetId) {
        this.targetId = targetId;
    }

    void resolveTarget(FxNewInstance inst) {
        this.target = inst;
        if (inst != null) {
            setJavaType(target.getJavaType());
        }
    }

    public String getTargetName() {
        if (target == null) {
            return null;
        }
        return target.getTypeName();
    }
    
    @Override
    public String getSourceName() {
        return FxXmlSymbols.FX_REFERENCE;
    }

    @Override
    public ElementHandle<TypeElement> getJavaType() {
        if (target == null) {
            return null;
        }
        return target.getJavaType();
    }
    
    public String getTargetId() {
        return targetId;
    }
    
    public FxNewInstance getTarget() {
        return target;
    }

    @Override
    public Kind getKind() {
        return Kind.Reference;
    }

    @Override
    public void accept(FxNodeVisitor v) {
        v.visitReference(this);
    }
    
}
