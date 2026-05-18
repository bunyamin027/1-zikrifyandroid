package com.bunyamin027.zikrify.model

data class Zikir(
    val id: String,
    val name: String,
    val englishName: String,
    val arabicText: String,
    val meaning: String,
    val englishMeaning: String,
    val defaultTarget: Int = 33,
    val moods: List<String> = emptyList(),
    val isEsma: Boolean = false
)

object ZikirData {
    val allZikirler = listOf(
        // MARK: - TEMEL ZİKİRLER (Sabah/Akşam)
        Zikir("preset-Sübhânallâh", "Sübhânallâh", "Subhanallah", "سُبْحَانَ اللّٰهِ", "Allah'ı tüm eksikliklerden tenzih ederim", "Glory be to Allah", 33, listOf(Moods.morning)),
        Zikir("preset-Elhamdülillâh", "Elhamdülillâh", "Alhamdulillah", "اَلْحَمْدُ لِلّٰهِ", "Hamd yalnızca Allah'a mahsustur", "All praise is due to Allah", 33, listOf(Moods.morning)),
        Zikir("preset-Allâhü Ekber", "Allâhü Ekber", "Allahu Akbar", "اَللّٰهُ أَكْبَرُ", "Allah en büyüktür", "Allah is the Greatest", 33, listOf(Moods.morning)),
        Zikir("preset-Lâ ilâhe illallâh", "Lâ ilâhe illallâh", "La ilaha illallah", "لَا اِلٰهَ اِلَّا اللّٰهُ", "Allah'tan başka ilah yoktur", "There is no god but Allah", 100, listOf(Moods.morning, Moods.night)),
        Zikir("preset-Sübhânallâhi ve bi-hamdihî", "Sübhânallâhi ve bi-hamdihî", "Subhanallahi wa bihamdihi", "سُبْحَانَ اللّٰهِ وَبِحَمْدِهِ", "Allah'ı hamd ile tesbih ederim", "Glory and praise be to Allah", 100, listOf(Moods.morning)),

        // MARK: - KALBE İNŞİRAH (Teselli / Huzur)
        Zikir("preset-Ya Selâm", "Ya Selâm", "Ya Salam", "يَا سَلَامُ", "Esenlik veren, selamete çıkaran", "The Source of Peace", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Latîf", "Ya Latîf", "Ya Latif", "يَا لَطِيفُ", "Lütuf ve keremi bol olan", "The Subtle One", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Kuddûs", "Ya Kuddûs", "Ya Quddus", "يَا قُدُّوسُ", "Hata ve noksanlıktan uzak olan", "The Holy", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Halîm", "Ya Halîm", "Ya Halim", "يَا حَلِيمُ", "Yumuşak davranan, hemen cezalandırmayan", "The Forbearing", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Habîr", "Ya Habîr", "Ya Khabir", "يَا خَبِيرُ", "Her şeyden haberdar olan", "The All-Aware", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Azîm", "Ya Azîm", "Ya Azim", "يَا عَظِيمُ", "Pek yüce ve ulu olan", "The Magnificent", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Kerîm-1", "Ya Kerîm", "Ya Karim", "يَا كَرِيمُ", "Lütfu ve keremi bol olan", "The Generous", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Raûf-1", "Ya Raûf", "Ya Rauf", "يَا رَؤُوفُ", "Çok şefkatli ve merhametli", "The Kind", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Velî", "Ya Velî", "Ya Wali", "يَا وَلِيُّ", "Müminlerin dostu ve yardımcısı", "The Protecting Friend", 100, listOf(Moods.consolation), true),
        Zikir("preset-Ya Bârr", "Ya Bârr", "Ya Barr", "يَا بَرُّ", "İyilik ve ihsanı bol olan", "The Source of All Goodness", 100, listOf(Moods.consolation), true),

        // MARK: - GÜVENDE HİSSET (Zorluk / Korunma)
        Zikir("preset-Hasbünallâh", "Hasbünallâhu ve ni'mel vekîl", "Hasbunallahu wa ni'mal wakeel", "حَسْبُنَا اللّٰهُ وَنِعْمَ الْوَكِيلُ", "Allah bize yeter, O ne güzel vekildir", "Allah is sufficient for us", 33, listOf(Moods.secure)),
        Zikir("preset-Ya Hafîz", "Ya Hafîz", "Ya Hafiz", "يَا حَفِيظُ", "Kollayıp gözeten, koruyan", "The Preserver", 100, listOf(Moods.secure), true),
        Zikir("preset-Ya Mü'min", "Ya Mü'min", "Ya Mu'min", "يَا مُؤْمِنُ", "Emniyet ve güven veren", "The Giver of Security", 100, listOf(Moods.secure), true),
        Zikir("preset-Ya Müheymin", "Ya Müheymin", "Ya Muhaymin", "يَا مُهَيْمِنُ", "Gözetip kollayan", "The Guardian", 100, listOf(Moods.secure), true),
        Zikir("preset-Ya Azîz", "Ya Azîz", "Ya Aziz", "يَا عَزِيزُ", "İzzet sahibi, mağlup edilemeyen", "The Almighty", 100, listOf(Moods.secure), true),
        Zikir("preset-Ya Cebbâr", "Ya Cebbâr", "Ya Jabbar", "يَا جَبَّارُ", "İstediğini zorla yaptıran", "The Compeller", 100, listOf(Moods.secure), true),
        Zikir("preset-Ya Kavî", "Ya Kavî", "Ya Qawi", "يَا قَوِيُّ", "Pek güçlü olan", "The All-Strong", 100, listOf(Moods.secure), true),
        Zikir("preset-Ya Metîn", "Ya Metîn", "Ya Matin", "يَا مَتِينُ", "Çok sağlam olan", "The Firm One", 100, listOf(Moods.secure), true),
        Zikir("preset-Ya Kadîr", "Ya Kadîr", "Ya Qadir", "يَا قَادِرُ", "Her şeye gücü yeten", "The Able", 100, listOf(Moods.secure), true),
        Zikir("preset-Ya Muktedir", "Ya Muktedir", "Ya Muqtadir", "يَا مُقْتَدِرُ", "İstediği gibi tasarruf eden", "The Powerful", 100, listOf(Moods.secure), true),

        // MARK: - İSTE VE OLSUN (Rızık / Kapıları Aç)
        Zikir("preset-Ya Fettâh", "Ya Fettâh", "Ya Fattah", "يَا فَتَّاحُ", "Kapıları açan, ferahlık veren", "The Opener", 100, listOf(Moods.ask), true),
        Zikir("preset-Ya Rezzâk", "Ya Rezzâk", "Ya Razzaq", "يَا رَزَّاقُ", "Rızık veren, ihtiyacı karşılayan", "The Provider", 100, listOf(Moods.ask), true),
        Zikir("preset-Ya Vehhâb", "Ya Vehhâb", "Ya Wahhab", "يَا وَهَّابُ", "Karşılıksız bolca veren", "The Bestower", 100, listOf(Moods.ask), true),
        Zikir("preset-Ya Ganî", "Ya Ganî", "Ya Ghani", "يَا غَنِيُّ", "Çok zengin", "The Self-Sufficient", 100, listOf(Moods.ask, Moods.gratitude), true),
        Zikir("preset-Ya Mugnî", "Ya Mugnî", "Ya Mughni", "يَا مُغْنِيُّ", "İstediğini zengin eden", "The Enricher", 100, listOf(Moods.ask), true),
        Zikir("preset-Ya Basit", "Ya Basit", "Ya Basit", "يَا بَاسِطُ", "Rızkı genişleten, ferahlık veren", "The Expander", 100, listOf(Moods.ask), true),
        Zikir("preset-Ya Vâsi", "Ya Vâsi", "Ya Wasi", "يَا وَاسِعُ", "İlmi ve merhameti her şeyi kuşatan", "The All-Embracing", 100, listOf(Moods.ask), true),
        Zikir("preset-Ya Kerîm-2", "Ya Kerîm", "Ya Karim", "يَا كَرِيمُ", "Cömertliği bol olan", "The Generous", 100, listOf(Moods.ask, Moods.gratitude, Moods.joy), true),
        Zikir("preset-Ya Mecîd", "Ya Mecîd", "Ya Majid", "يَا مَجِيدُ", "Şanı yüce ve ulu olan", "The Glorious", 100, listOf(Moods.ask, Moods.gratitude), true),
        Zikir("preset-Ya Allah", "Ya Allah", "Ya Allah", "يَا اَللّٰهُ", "İlahi varlığın ismi", "Allah", 100, listOf(Moods.ask), true),

        // MARK: - ARINMA (Tövbe)
        Zikir("preset-Estağfirullâh", "Estağfirullâh", "Astaghfirullah", "أَسْتَغْفِرُ اللَّٰهَ", "Allah'tan bağışlanma dilerim", "I seek forgiveness", 100, listOf(Moods.purification)),
        Zikir("preset-Ya Tevvâb", "Ya Tevvâb", "Ya Tawwab", "يَا تَوَّابُ", "Tövbeleri kabul eden", "The Accepter of Repentance", 100, listOf(Moods.purification), true),
        Zikir("preset-Ya Gaffâr", "Ya Gaffâr", "Ya Ghaffar", "يَا غَفَّارُ", "Günahları çokça bağışlayan", "The All-Forgiving", 100, listOf(Moods.purification), true),
        Zikir("preset-Ya Gafûr", "Ya Gafûr", "Ya Ghafur", "يَا غَفُورُ", "Mağfireti bol olan", "The Most Forgiving", 100, listOf(Moods.purification), true),
        Zikir("preset-Ya Afüvv", "Ya Afüvv", "Ya Afuww", "يَا عَفُوُّ", "Affı çok olan, günahları silen", "The Pardoner", 100, listOf(Moods.purification), true),
        Zikir("preset-Ya Kuddûs-2", "Ya Kuddûs", "Ya Quddus", "يَا قُدُّوسُ", "Her türlü kirden arınmış", "The Holy", 100, listOf(Moods.purification), true),
        Zikir("preset-Ya Raûf-2", "Ya Raûf", "Ya Rauf", "يَا رَؤُوفُ", "Şefkatiyle affeden", "The Kind", 100, listOf(Moods.purification, Moods.gratitude), true),
        Zikir("preset-Ya Mütekebbir", "Ya Mütekebbir", "Ya Mutakabbir", "يَا مُتَكَبِّرُ", "Büyüklüğünü her şeyde gösteren", "The Supreme", 100, listOf(Moods.purification), true),
        Zikir("preset-Ya Hâlik", "Ya Hâlik", "Ya Khaliq", "يَا خَالِقُ", "Yoktan var eden", "The Creator", 100, listOf(Moods.purification), true),
        Zikir("preset-Ya Bâri", "Ya Bâri", "Ya Bari", "يَا بَارِئُ", "Eksiksiz yaratan", "The Evolver", 100, listOf(Moods.purification), true),

        // MARK: - BEREKET VE ŞÜKÜR
        Zikir("preset-Ya Şekûr", "Ya Şekûr", "Ya Shakur", "يَا شَكُورُ", "Şükre bolca karşılık veren", "The Appreciative", 100, listOf(Moods.gratitude), true),
        Zikir("preset-Ya Hamid", "Ya Hamid", "Ya Hamid", "يَا حَمِيدُ", "Hamd edilmeye layık olan", "The Praiseworthy", 100, listOf(Moods.gratitude), true),
        Zikir("preset-Ya Mukît", "Ya Mukît", "Ya Muqit", "يَا مُقِيتُ", "Her canlının rızkını veren", "The Maintainer", 100, listOf(Moods.gratitude), true),
        Zikir("preset-Ya Latîf-2", "Ya Latîf", "Ya Latif", "يَا لَطِيفُ", "Lütfu her şeyi kuşatan", "The Subtle", 100, listOf(Moods.gratitude), true),
        Zikir("preset-Ya Rezzâk-2", "Ya Rezzâk", "Ya Razzaq", "يَا رَزَّاقُ", "Rızıklandıran", "The Provider", 100, listOf(Moods.gratitude), true),

        // MARK: - NEŞENİ ARTIR (Mutluluk / Sevgi)
        Zikir("preset-Ya Vedûd", "Ya Vedûd", "Ya Wadud", "يَا وَدُودُ", "Çok seven ve sevilmeye layık", "The Loving", 100, listOf(Moods.joy), true),
        Zikir("preset-Ya Nûr", "Ya Nûr", "Ya Nur", "يَا نُورُ", "Âlemleri nurlandıran", "The Light", 100, listOf(Moods.joy), true),
        Zikir("preset-Ya Mucîb", "Ya Mucîb", "Ya Mujib", "يَا مُجِيبُ", "Duaları kabul eden", "The Responsive", 100, listOf(Moods.joy), true),
        Zikir("preset-Ya Hâfıd", "Ya Hâfıd", "Ya Hafid", "يَا خَافِضُ", "Yükselten ve alçaltan", "The Abaser", 100, listOf(Moods.joy), true),
        Zikir("preset-Ya Râfi", "Ya Râfi", "Ya Rafi", "يَا رَافِعُ", "Yükselten", "The Exalter", 100, listOf(Moods.joy), true),
        Zikir("preset-Ya Muizz", "Ya Muizz", "Ya Mu'izz", "يَا مُعِزُّ", "İzzet veren", "The Giver of Honor", 100, listOf(Moods.joy), true),
        Zikir("preset-Ya Semi", "Ya Semi", "Ya Sami", "يَا سَمِيعُ", "Her şeyi işiten", "The All-Hearing", 100, listOf(Moods.joy), true),
        Zikir("preset-Ya Basîr", "Ya Basîr", "Ya Basir", "يَا بَصِيرُ", "Her şeyi gören", "The All-Seeing", 100, listOf(Moods.joy), true),
        Zikir("preset-Ya Habîr-2", "Ya Habîr", "Ya Khabir", "يَا خَبِيرُ", "Haberdar olan", "The All-Aware", 100, listOf(Moods.joy), true)
    )
}
