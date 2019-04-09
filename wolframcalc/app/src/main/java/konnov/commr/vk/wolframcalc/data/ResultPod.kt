package konnov.commr.vk.wolframcalc.data

class ResultPod {

    lateinit var title: String

    lateinit var description: String

    override fun toString(): String {
        return "title: $title, \ndescription: $description"
    }
}