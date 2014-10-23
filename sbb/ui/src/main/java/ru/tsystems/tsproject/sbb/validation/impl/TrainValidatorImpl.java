package ru.tsystems.tsproject.sbb.validation.impl;

import ru.tsystems.tsproject.sbb.helpers.StringHelper;
import ru.tsystems.tsproject.sbb.transferObjects.TrainTO;
import ru.tsystems.tsproject.sbb.validation.Validator;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by apple on 22.10.2014.
 */
public class TrainValidatorImpl extends Validator<TrainTO> {
    private static final Pattern namePattern = Pattern.compile("[0-9A-Za-z\\p{L}]{0,100}/u");

    public TrainValidatorImpl(TrainTO obj) {
        super(obj);
    }

    @Override
    protected Map<String, String> validate(TrainTO train) {
        Map<String, String> errors = new HashMap<String, String>();
        if (!namePattern.matcher(train.getName()).matches()) {
            errors.put("name", StringHelper.encode(
                    "имя должно содержать только буквы и символы и не превышать 100 символов"));
        }
        if (train.getNumber() <= 0 || train.getNumber() >= 10000000) {
            errors.put("number", StringHelper.encode(
                    "номер должен быть положительным и состоять из 7 цифр"));
        }
        if (train.getSeatCount() <= 0 || train.getSeatCount() >= 10000) {
            errors.put("seatCount", StringHelper.encode(
                    "количество мест должно быть положительным и состоять из 4 цифр"));
        }
        return errors;
    }
}
