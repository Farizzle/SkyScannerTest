package com.example.skyscanner.data.remote

import com.google.gson.Gson

object FakeData {

    private fun json(): String {
        val oldJson = "{\n" +
                "  \"itineraries\": [\n" +
                "    {\n" +
                "      \"id\": \"it_1\",\n" +
                "      \"legs\": [\n" +
                "        \"leg_1\",\n" +
                "        \"leg_4\"\n" +
                "      ],\n" +
                "      \"price\": \"£35\",\n" +
                "      \"agent\": \"Wizzair.com\",\n" +
                "      \"agent_rating\": 9.1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"it_2\",\n" +
                "      \"legs\": [\n" +
                "        \"leg_2\",\n" +
                "        \"leg_5\"\n" +
                "      ],\n" +
                "      \"price\": \"£115\",\n" +
                "      \"agent\": \"British Airways\",\n" +
                "      \"agent_rating\": 9.3\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"it_3\",\n" +
                "      \"legs\": [\n" +
                "        \"leg_3\",\n" +
                "        \"leg_6\"\n" +
                "      ],\n" +
                "      \"price\": \"£90\",\n" +
                "      \"agent\": \"Lufthansa\",\n" +
                "      \"agent_rating\": 8.9\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"it_4\",\n" +
                "      \"legs\": [\n" +
                "        \"leg_1\",\n" +
                "        \"leg_5\"\n" +
                "      ],\n" +
                "      \"price\": \"£105\",\n" +
                "      \"agent\": \"Trip.com\",\n" +
                "      \"agent_rating\": 9.5\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"it_5\",\n" +
                "      \"legs\": [\n" +
                "        \"leg_1\",\n" +
                "        \"leg_6\"\n" +
                "      ],\n" +
                "      \"price\": \"£195\",\n" +
                "      \"agent\": \"Trip.com\",\n" +
                "      \"agent_rating\": 9.5\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"it_6\",\n" +
                "      \"legs\": [\n" +
                "        \"leg_2\",\n" +
                "        \"leg_4\"\n" +
                "      ],\n" +
                "      \"price\": \"£93\",\n" +
                "      \"agent\": \"Kiwi.com\",\n" +
                "      \"agent_rating\": 8.0\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"it_7\",\n" +
                "      \"legs\": [\n" +
                "        \"leg_3\",\n" +
                "        \"leg_4\"\n" +
                "      ],\n" +
                "      \"price\": \"£42\",\n" +
                "      \"agent\": \"CheapFligths\",\n" +
                "      \"agent_rating\": 10.0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"legs\": [\n" +
                "    {\n" +
                "      \"id\": \"leg_1\",\n" +
                "      \"departure_airport\": \"BUD\",\n" +
                "      \"arrival_airport\": \"LTN\",\n" +
                "      \"departure_time\": \"2020-10-31T15:35\",\n" +
                "      \"arrival_time\": \"2020-10-31T17:00\",\n" +
                "      \"stops\": 0,\n" +
                "      \"airline_name\": \"Wizz Air\",\n" +
                "      \"airline_id\": \"WZ\",\n" +
                "      \"duration_mins\": 145\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"leg_2\",\n" +
                "      \"departure_airport\": \"BUD\",\n" +
                "      \"arrival_airport\": \"LHR\",\n" +
                "      \"departure_time\": \"2020-10-31T12:05\",\n" +
                "      \"arrival_time\": \"2020-10-31T17:00\",\n" +
                "      \"stops\": 1,\n" +
                "      \"airline_name\": \"British Airways\",\n" +
                "      \"airline_id\": \"BA\",\n" +
                "      \"duration_mins\": 190\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"leg_3\",\n" +
                "      \"departure_airport\": \"BUD\",\n" +
                "      \"arrival_airport\": \"LGW\",\n" +
                "      \"departure_time\": \"2020-10-31T22:35\",\n" +
                "      \"arrival_time\": \"2020-11-01T00:10\",\n" +
                "      \"stops\": 0,\n" +
                "      \"airline_name\": \"Lufthansa\",\n" +
                "      \"airline_id\": \"LH\",\n" +
                "      \"duration_mins\": 155\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"leg_4\",\n" +
                "      \"departure_airport\": \"LTN\",\n" +
                "      \"arrival_airport\": \"BUD\",\n" +
                "      \"departure_time\": \"2020-11-11T19:45\",\n" +
                "      \"arrival_time\": \"2020-11-11T21:10\",\n" +
                "      \"stops\": 0,\n" +
                "      \"airline_name\": \"Wizz Air\",\n" +
                "      \"airline_id\": \"WZ\",\n" +
                "      \"duration_mins\": 145\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"leg_5\",\n" +
                "      \"departure_airport\": \"LHR\",\n" +
                "      \"arrival_airport\": \"BUD\",\n" +
                "      \"departure_time\": \"2020-11-11T11:25\",\n" +
                "      \"arrival_time\": \"2020-11-11T19:10\",\n" +
                "      \"stops\": 1,\n" +
                "      \"airline_name\": \"British Airways\",\n" +
                "      \"airline_id\": \"BA\",\n" +
                "      \"duration_mins\": 190\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"leg_6\",\n" +
                "      \"departure_airport\": \"LGW\",\n" +
                "      \"arrival_airport\": \"BUD\",\n" +
                "      \"departure_time\": \"2020-11-11T08:10\",\n" +
                "      \"arrival_time\": \"2020-11-11T11:40\",\n" +
                "      \"stops\": 0,\n" +
                "      \"airline_name\": \"Lufthansa\",\n" +
                "      \"airline_id\": \"LH\",\n" +
                "      \"duration_mins\": 150\n" +
                "    }\n" +
                "  ]" +
                "}"
        return oldJson.replace("\n", "").replace("\\\\", "").replace("\\", "")
    }

    fun getItineraries() : ItinerariesResponse {
        val gson = Gson()
        return gson.fromJson(json(), ItinerariesResponse::class.java)
    }

}