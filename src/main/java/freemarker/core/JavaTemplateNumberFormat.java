/*
 * Copyright 2014 Attila Szegedi, Daniel Dekany, Jonathan Revusky
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
package freemarker.core;

import java.text.NumberFormat;

import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

final class JavaTemplateNumberFormat extends BackwardCompatibleTemplateNumberFormat {
    
    private final String pattern;
    private final NumberFormat javaNumberFormat;

    public JavaTemplateNumberFormat(NumberFormat javaNumberFormat, String pattern) {
        this.pattern = pattern;
        this.javaNumberFormat = javaNumberFormat;
    }

    @Override
    public String format(TemplateNumberModel numberModel) throws UnformattableNumberException, TemplateModelException {
        Number number = numberModel.getAsNumber();
        if (number == null) {
            throw new UnformattableNumberException("The number model has contained null as the number.");
        }
        return format(number);
    }

    @Override
    public <MO extends TemplateMarkupOutputModel> MO format(TemplateNumberModel dateModel,
            MarkupOutputFormat<MO> outputFormat) throws UnformattableNumberException, TemplateModelException {
        return null;
    }

    @Override
    public String getDescription() {
        return pattern;
    }

    @Override
    public boolean isLocaleBound() {
        return true;
    }

    @Override
    String format(Number number) throws UnformattableNumberException {
        return javaNumberFormat.format(number);
    }

    public NumberFormat getJavaNumberFormat() {
        return javaNumberFormat;
    }

}
