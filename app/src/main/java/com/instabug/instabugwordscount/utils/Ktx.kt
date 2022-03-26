fun List<String>.getEachCount() = this.groupingBy { it }.eachCount()

fun String.removePunctuation() =
    this.replace("""[\|\@\<\>\[\]\"\'\.,-\/#\?!$%\^&\*\+;:{}=\-_`~()]""".toRegex(), ",")
        .replace(",,,,", ",")
        .replace(",,,", ",")
        .replace(",,", ",")
        .replace(",", ",")
        .replace(" ", ",")
        .split(",")
        .filterNot { it.isBlank()  || it.length <= 3 || it.contains("[0-9]+".toRegex()) }