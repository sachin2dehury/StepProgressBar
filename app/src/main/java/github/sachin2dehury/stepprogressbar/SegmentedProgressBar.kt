package github.sachin2dehury.stepprogressbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2

class SegmentedProgressBar : View {

    var segmentCount: Int = resources.getInteger(R.integer.default_segments_count)
        set(value) {
            field = value
            this.initSegments()
        }

    var margin: Int = resources.getDimensionPixelSize(R.dimen.default_segment_margin)
    var radius: Int = resources.getDimensionPixelSize(R.dimen.default_corner_radius)
    var segmentStrokeWidth: Int =
        resources.getDimensionPixelSize(R.dimen.default_segment_stroke_width)

    var segmentBackgroundColor: Int = Color.WHITE
    var segmentSelectedBackgroundColor: Int =
        context.getThemeColor(android.R.attr.colorAccent)
    var segmentStrokeColor: Int = Color.BLACK
    var segmentSelectedStrokeColor: Int = Color.BLACK

    private var segments = mutableListOf<Segment>()

    //Drawing
    val strokeApplicable: Boolean
        get() = segmentStrokeWidth * 4 <= measuredHeight

    val segmentWidth: Float
        get() = (measuredWidth - margin * (segmentCount - 1)).toFloat() / segmentCount

    var viewPager: ViewPager2? = null
        @SuppressLint("ClickableViewAccessibility")
        set(value) {
            field = value
            if (value == null) {
                viewPager?.unregisterOnPageChangeCallback(callBack)
            } else {
                viewPager?.registerOnPageChangeCallback(callBack)
            }
        }

    private val callBack = CallBack()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.SegmentedProgressBar, 0, 0)

        segmentCount =
            typedArray.getInt(R.styleable.SegmentedProgressBar_totalSegments, segmentCount)

        margin =
            typedArray.getDimensionPixelSize(
                R.styleable.SegmentedProgressBar_segmentMargins,
                margin
            )
        radius =
            typedArray.getDimensionPixelSize(
                R.styleable.SegmentedProgressBar_segmentCornerRadius,
                radius
            )
        segmentStrokeWidth =
            typedArray.getDimensionPixelSize(
                R.styleable.SegmentedProgressBar_segmentStrokeWidth,
                segmentStrokeWidth
            )

        segmentBackgroundColor =
            typedArray.getColor(
                R.styleable.SegmentedProgressBar_segmentBackgroundColor,
                segmentBackgroundColor
            )
        segmentSelectedBackgroundColor =
            typedArray.getColor(
                R.styleable.SegmentedProgressBar_segmentSelectedBackgroundColor,
                segmentSelectedBackgroundColor
            )

        segmentStrokeColor =
            typedArray.getColor(
                R.styleable.SegmentedProgressBar_segmentStrokeColor,
                segmentStrokeColor
            )
        segmentSelectedStrokeColor =
            typedArray.getColor(
                R.styleable.SegmentedProgressBar_segmentSelectedStrokeColor,
                segmentSelectedStrokeColor
            )

        typedArray.recycle()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        segments.forEachIndexed { index, segment ->
            val drawingComponents = getDrawingComponents(segment, index)
            drawingComponents.first.forEachIndexed { drawingIndex, rectangle ->
                canvas?.drawRoundRect(
                    rectangle,
                    radius.toFloat(),
                    radius.toFloat(),
                    drawingComponents.second[drawingIndex]
                )
            }
        }
    }

    inner class CallBack : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            invalidate()
            Log.w("Test", position.toString())
            segments.mapIndexed { index, segment ->
                if (index <= position) {
                    Log.w("Test Index", index.toString())
                    segment.animationState = Segment.AnimationState.ANIMATED
                } else {
                    Log.w("Test Index Not", index.toString())
                    segment.animationState = Segment.AnimationState.IDLE
                }
            }
        }
    }


    private fun initSegments() {
        this.segments.clear()
        segments.addAll(List(segmentCount) { Segment() })
        this.segments.map { it.animationState = Segment.AnimationState.IDLE }
        this.invalidate()
    }
}