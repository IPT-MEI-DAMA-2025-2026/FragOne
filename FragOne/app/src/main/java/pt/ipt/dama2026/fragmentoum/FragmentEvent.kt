package pt.ipt.dama2026.fragmentoum

data class FragmentEvent(val id: Int,
                         val timestamp: Long = System.currentTimeMillis())
