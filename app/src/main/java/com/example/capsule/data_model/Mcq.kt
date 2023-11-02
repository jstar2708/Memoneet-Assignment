package com.example.capsule.data_model

class Mcq(
    val question: String,
    val options: Array<String>,
    val answer: Int) {

    companion object {
        val questionList = ArrayList<Mcq>()

        fun populateList() {
            questionList.add(
                Mcq(
                    "Q1. The infraorder Simiformes (the anthropoids) includes only:",
                    arrayOf(
                        "Old World monkeys and apes",
                        "Old World monkeys, apes, and humans",
                        "Old and New World monkeys, apes, and humans",
                        "lemurs, lorises, monkeys, apes, and humans"
                    ),
                    3
                )
            )

            questionList.add(
                Mcq(
                    "Q2. Compared to the prosimians, monkeys are:",
                    arrayOf(
                        "generally larger",
                        "more intelligent",
                        "more often diurnal",
                        "all of the above"
                    ),
                    4
                )
            )

            questionList.add(
                Mcq(
                    "Q3. The New World monkeys:",
                    arrayOf(
                        "are members of the parvorder Catarrhini",
                        "have relatively flat noses with somewhat sideways projecting nostrils separated by a wide septum",
                        "have the same numbers of each type of tooth as the Old World monkeys",
                        "all of the above"
                    ),
                    2
                )
            )

            questionList.add(
                Mcq(
                    "Q4. Some of the New World monkeys:",
                    arrayOf(
                        "have prehensile tails",
                        "2.1.2.3 dental formulas",
                        "ischial callosities",
                        "none of the above"
                    ),
                    1
                )
            )

            questionList.add(
                Mcq(
                    "Q5. In the Catarrhini dental formula, 2.1.2.3, the numbers refer to:",
                    arrayOf(
                        "the relative length of teeth",
                        "the total number of teeth in the mouth",
                        "the number of each type of tooth in one quadrant of the mouth",
                        "none of the above"
                    ),
                    3
                )
            )
        }
    }
}

