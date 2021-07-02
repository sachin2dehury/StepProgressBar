package github.sachin2dehury.stepprogressbar

interface SegmentedProgressBarListener {

    fun onPage(oldPageIndex: Int, newPageIndex: Int)

    fun onFinished()
}