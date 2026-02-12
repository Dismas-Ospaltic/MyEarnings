package com.gw.myearnings.utils

import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    fun formatYearMonth(input: String): String {
        return try {
            val normalized = when {
                // Format: 2026-02
                Regex("""\d{4}-\d{2}""").matches(input) -> input

                // Format: 02-2026 → convert to 2026-02
                Regex("""\d{2}-\d{4}""").matches(input) -> {
                    val parts = input.split("-")
                    "${parts[1]}-${parts[0]}"
                }

                else -> return input
            }

            val yearMonth = YearMonth.parse(normalized)
            yearMonth.format(
                DateTimeFormatter.ofPattern("MMMM, yyyy", Locale.ENGLISH)
            )

        } catch (e: Exception) {
            input
        }
    }
}
