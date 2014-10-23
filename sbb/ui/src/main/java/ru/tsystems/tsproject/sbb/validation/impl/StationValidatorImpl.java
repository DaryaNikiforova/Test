package ru.tsystems.tsproject.sbb.validation.impl;

import ru.tsystems.tsproject.sbb.helpers.StringHelper;
import ru.tsystems.tsproject.sbb.transferObjects.StationTO;
import ru.tsystems.tsproject.sbb.validation.Validator;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class StationValidatorImpl extends Validator<StationTO> {

    private static final Pattern pattern = Pattern.compile("[0-9A-Za-z\\p{L}]{0,100}/u");

    public StationValidatorImpl(StationTO obj) {
        super(obj);
    }

    @Override
    protected Map<String, String> validate(StationTO station) {
        Map<String, String> errors = new HashMap<String, String>();
        if (station.getName() == null || station.getName().isEmpty()) {
            errors.put("name", StringHelper.encode("необходимо задать имя"));
        }
        else if (!pattern.matcher(station.getName()).matches()) {
            errors.put("name", StringHelper.encode("имя должно содержать только буквы и символы и не превышать 100 символов"));
        }
        return errors;
    }
}
