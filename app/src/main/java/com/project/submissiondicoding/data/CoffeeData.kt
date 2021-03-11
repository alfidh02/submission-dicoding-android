package com.project.submissiondicoding.data

import com.project.submissiondicoding.R
import com.project.submissiondicoding.model.Coffee

object CoffeeData {
    private val coffeeNames = arrayOf(
        "Sebiji Kopi",
        "KYIV Coffee",
        "ORIGN.ID",
        "MKA Kupie",
        "Selowla Coffee",
        "Coffee Day",
        "Serambi Kuphi",
        "SOVA Kopi",
        "KOKTONG Kopi",
        "Gara-Gara Kopi"
    )

    private val coffeeDetails = arrayOf(
        "Tempat kopi yang disertai halaman rooftop dengan atap terbuka membuat siapa saja yang mengunjungi Sebiji Kopi ingin mengunjunginya lagi. Suasana nyaman dan ngobrol menjadi lebih hangat dengan kopi pilihan terbaik dari Sebiji Kopi. Terdapat beraneka ragam menu kopi dan dessert yang pantas untuk dicoba.",
        "Berlokasi di Jl. Jendral Sudirman, tempat kopi ini juga tak kalah dalam menawarkan suasana cozy dan fokus bagi para pemuda yang ingin menghabiskan waktunya baik untuk sekadar ngopi ataupun bekerja sembari ngopi. Selain untuk ngopi, tempat ini juga dilengkapi dengan fasilitas pangkas yang terpercaya. KYIV kopi, nyaman!",
        "ORIGN.ID, sekilas tampak tidak meyakinkan sebagai sebuah nama tempat kopi. Namun tempat yang dilengkapi dengan rooftop beratap dan kursi yang mengarahkan pemandangan ke arah lalu lintas perkotaan membantah hal tersebut. Kopi dan juga makanan banyak tersedia disini. Selain untuk sekadar ngopi, tempat ini juga bagus untuk kamu yang sekadar ingin mengamati lalu lintas perkotaan kota Binjai dan orang-orang yang pergi dan pulang mencari nafkah untuk keluarga di rumah. Cocok!",
        "Khasnya Kopi Aceh Gayo (Meuligoe Kupi Atjeh), banyak suasana menarik yang bisa kamu dapatkan ketika nongkrong di MKA Kupie. Ngobrol asik disertai live music terkadang hadir di hari-hari weekend sembari melepas penatmu setelah bekerja seminggu penuh. Staf-staf yang ramah dan menu yang tak kalah enaknya ini membuat siapapun yang berkunjung ke MKA ingin mengunjunginya lagi.",
        "Selowla! adalah sebuah kata yang diucapkan ketika seseorang terlanjur marah ataupun emosi terhadap suatu hal. Sama halnya di selowla kopi, tempat yang cocok untuk mendinginkan diri (karena ada AC di ruangan dalamnya) dan melepas emosi dengan meminum kopi sembari menikmati live music yang terkadang ditampilkan di malam hari. Menu yang beragam membuat pengunjung tak hanya bisa meminum kopi, namun juga dapat menikmati roti bakar blueberry dan pisang bakar yang dilumuri dengan coklat.",
        "Coffee Day, salah satu tempat ternyaman di kota Binjai untuk meminum seruput kopi ataupun menikmati makanan bersama keluarga. Tempat ini juga sering digunakan sebagai tempat meeting bagi para instansi ataupun orang-orang berkepentingan karena adanya fasilitas meeting disertai ruangan AC. Menu yang enak dan sesuai dengan harganya juga menjadi daya tarik tersendiri bagi para pengunjungnya. Ayo ngopi di Coffee Day!",
        "Tempat ini terkenal dengan konsep outdoornya yang sangat nyaman dan seakan-akan menyatu dengan alam. Berbicara tentang apapun menjadi indah dan hangat karena sapuan angin dan udara yang sejuk. Kopi-kopi yang disajikan pun tak kalah hangatnya. Harganya yang tidak terlalu mahal ini menjadikan Serambi Kuphi sebagai tempat favorit untuk nongkrong di kala tanggal tua :)",
        "Tampak nyaman di dalam dan di luar, suasana yang ditawarkan oleh tempat kopi ini sangat menarik. Konsep indoor dan outdoornya sama-sama menarik perhatian pengunjung yang ingin nongkrong ataupun reuni dengan teman-temannya. Menu yang ada disini juga beragam, sehingga pengunjung tak perlu takut kehabisan pilihan ketika nongkrong di tempat ini seterusnya.",
        "Legenda. Tmepat kopi yang sangat populer di kota Binjai. Siapa pun pasti pernah mendengar nama tempat kopi yang satu ini, sebab memang kualitas tempatnya sangat bagus! Kualitas menunya juga tak kalah bagus. Dengan harga yang sepadan dengan menu yang ditawarkan, Koktong menjadi tempat kopi yang cocok digunakan semua kalangan, mulai dari pelajar, pekerja, siapa pun itu. Kopi yang disajikan juga memiliki rasa yang khas di hati para pengunjungnya. Koktong Kopi benar-benar top dan tak lekang oleh cabang-cabangnya.",
        "Seperti sebuah kalimat, namun nama tempat kopi ini benar-benar ada di kota Binjai. Berlokasi di Jl. Kartini, tempat ini menawarkan konsep yang unik dan menarik bagi orang-orang yang hanya ingin sekadar duduk dan ngobrol ataupun membaca buku sendiri. Tempat ini khas dengan meja panjangnya yang membuatnya cocok untuk dijadikan tempat nongkrong bagi banyak orang. Terdapat juga fresh milk yang sangat segar yang menjadi menu favorit saya pribadi untuk menyegarkan diri dan menambah tenaga untuk beraktivitas. Perlu diingat, Gara-Gara Kopi juga menawarkan promo sebelum jam 11 pagi dengan penurunan harga beberapa menunya, oleh karena itu cocok sekali untuk kamu yang ingin ngopi ataupun meminum susu di pagi hari sebelum berangkat beraktivitas."
    )

    private val coffeeImages = intArrayOf(
        R.drawable.sebiji,
        R.drawable.kyiv,
        R.drawable.orign,
        R.drawable.mka,
        R.drawable.selowla,
        R.drawable.coffeeday,
        R.drawable.serambi,
        R.drawable.sova,
        R.drawable.koktong,
        R.drawable.ggk
    )

    private val coffeLoc = arrayOf(
        "3.596301412786836,98.48109495058493",
        "3.6051532190618225,98.48540582665944",
        "3.609506104991049,98.49518032418247",
        "3.6085382502983174,98.51251854089908",
        "3.5964891897221882,98.4804504978239",
        "3.601561874253792,98.48127530948054",
        "3.5971306204686937,98.4809474272623",
        "3.5946175874983637,98.48156441300033",
        "3.6032257913779406,98.48377698344619",
        "3.6011734127153523,98.48588293892828"
    )

    val listData: ArrayList<Coffee>
        get() {
            val list = arrayListOf<Coffee>()
            for (position in coffeeNames.indices) {
                val coffee = Coffee()
                coffee.name = coffeeNames[position]
                coffee.detail = coffeeDetails[position]
                coffee.photo = coffeeImages[position]
                coffee.location = coffeLoc[position]
                list.add(coffee)
            }
            return list
        }
}