package com.microblink.blinkid.demo.result.extract;

import android.content.Context;

import com.microblink.recognizers.BaseRecognitionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by igor on 12/4/14.
 */
public class BaseRecognitionResultExtractor implements IBaseRecognitionResultExtractor {
    /**
     * Extracted data storage.
     */
    protected List<RecognitionResultEntry> mExtractedData;

    private Map<String, String> mNameMappings;
    Context mContext;

    public BaseRecognitionResultExtractor(Context mContext) {
        this.mContext = mContext;
        mExtractedData = new ArrayList<>();
    }

    @Override
    public List<RecognitionResultEntry> extractData(BaseRecognitionResult result) {
        if (result == null) {
            return mExtractedData;
        }
        for (String s : result.getData().keySet()) {
            String value = result.getStringElement(s);
            mExtractedData.add(new RecognitionResultEntry(s,value));
        }
        return mExtractedData;
    }

    private String resolveUIName(String key) {
        if (mNameMappings.containsKey(key)) {
            return mNameMappings.get(key);
        }
        return key;
    }
}
