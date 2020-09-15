package com.accellion.moviecompanion;

import android.content.Context;
import android.widget.Filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataHelper {
    private static final String COLORS_FILE_NAME = "colors.json";

    private static List<MovieWrapper> sColorWrappers = new ArrayList<>();

    private static List<MovieSuggestion> sColorSuggestions =
            new ArrayList<>(Arrays.asList(
                    new MovieSuggestion("green"),
                    new MovieSuggestion("blue"),
                    new MovieSuggestion("pink"),
                    new MovieSuggestion("purple"),
                    new MovieSuggestion("brown"),
                    new MovieSuggestion("gray"),
                    new MovieSuggestion("Granny Smith Apple"),
                    new MovieSuggestion("Indigo"),
                    new MovieSuggestion("Periwinkle"),
                    new MovieSuggestion("Mahogany"),
                    new MovieSuggestion("Maize"),
                    new MovieSuggestion("Mahogany"),
                    new MovieSuggestion("Outer Space"),
                    new MovieSuggestion("Melon"),
                    new MovieSuggestion("Yellow"),
                    new MovieSuggestion("Orange"),
                    new MovieSuggestion("Red"),
                    new MovieSuggestion("Orchid")));

    public interface OnFindColorsListener {
        void onResults(List<MovieWrapper> results);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<MovieSuggestion> results);
    }

    public static List<MovieSuggestion> getHistory(Context context, int count) {

        List<MovieSuggestion> suggestionList = new ArrayList<>();
        MovieSuggestion colorSuggestion;
        for (int i = 0; i < sColorSuggestions.size(); i++) {
            colorSuggestion = sColorSuggestions.get(i);
            colorSuggestion.setIsHistory(true);
            suggestionList.add(colorSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    public static void resetSuggestionsHistory() {
        for (MovieSuggestion colorSuggestion : sColorSuggestions) {
            colorSuggestion.setIsHistory(false);
        }
    }

    public static void findSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                DataHelper.resetSuggestionsHistory();
                List<MovieSuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (MovieSuggestion suggestion : sColorSuggestions) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                } else {
                    for (MovieSuggestion suggestion : sColorSuggestions) {
                        suggestionList.add(suggestion);
                        if (limit != -1 && suggestionList.size() == limit) {
                            break;
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, (lhs, rhs) -> lhs.getIsHistory() ? -1 : 0);
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<MovieSuggestion>) results.values);
                }
            }
        }.filter(query);

    }


    public static void findColors(Context context, String query, final OnFindColorsListener listener) {
        initColorWrapperList(context);

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<MovieWrapper> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (MovieWrapper color : sColorWrappers) {
                        if (color.getName().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(color);
                        }
                    }

                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<MovieWrapper>) results.values);
                }
            }
        }.filter(query);

    }

    private static void initColorWrapperList(Context context) {

        if (sColorWrappers.isEmpty()) {
            String jsonString = loadJson(context);
            sColorWrappers = deserializeColors(jsonString);
        }
    }

    private static String loadJson(Context context) {

        String jsonString;

        try {
            InputStream is = context.getAssets().open(COLORS_FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonString;
    }

    private static List<MovieWrapper> deserializeColors(String jsonString) {

        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<MovieWrapper>>() {
        }.getType();
        return gson.fromJson(jsonString, collectionType);
    }
}
