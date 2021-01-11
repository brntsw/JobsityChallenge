package bruno.com.jobsitychallenge.extension

fun List<String>.listToWordsSeparatedByCommas() : String {
    var text = ""

    this.forEachIndexed { index, word ->
        text += if(index == this.size - 1)
            word
        else
            "$word, "
    }

    return text
}