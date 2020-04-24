package com.example.pokedex.util.view

import android.view.View
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlin.math.abs

class MySnapHelper(val listener: ((View) -> Unit)) : SnapHelper() {

    private var previousClosestPosition = 0
    private val snapCount = 1

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray {
        val out = IntArray(2)
        if (layoutManager.canScrollHorizontally()) {
            out[0] = getDistance(
                targetView,
                OrientationHelper.createHorizontalHelper(layoutManager)
            )
        } else {
            out[0] = 0
        }
        return out
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        val childCount = layoutManager.childCount
        if (childCount == 0) return null

        val helper = OrientationHelper.createHorizontalHelper(layoutManager)
        val containerPosition = getContainerPosition(helper)
        var closestChild: View? = null
        var closestPosition = RecyclerView.NO_POSITION
        var absClosest = Int.MAX_VALUE

        for (i in 0 until childCount) {
            val child = layoutManager.getChildAt(i) ?: return closestChild

            val childPosition = getChildPosition(child, helper)
            val absDistance = abs(childPosition - containerPosition)
            if (helper.getDecoratedStart(child) == 0 &&
                previousClosestPosition != 0 &&
                layoutManager.getPosition(child) == 0) {
                //RecyclerView reached start
                closestChild = child
                closestPosition = layoutManager.getPosition(closestChild)
                break
            }
            if (helper.getDecoratedEnd(child) == helper.totalSpace &&
                previousClosestPosition != layoutManager.itemCount - 1 &&
                layoutManager.getPosition(child) == layoutManager.itemCount - 1) {
                //RecyclerView reached end
                closestChild = child
                closestPosition = layoutManager.getPosition(closestChild)
                break
            }
            if (previousClosestPosition ==
                layoutManager.getPosition(child) && getDistance(child, helper) == 0) {
                //child is already set to the position.
                closestChild = child
                break
            }
            if (layoutManager.getPosition(child) % snapCount != 0) {
                continue
            }
            if (absDistance < absClosest) {
                absClosest = absDistance
                closestChild = child
                closestPosition = layoutManager.getPosition(closestChild)
            }
        }
        previousClosestPosition = if (closestPosition == RecyclerView.NO_POSITION) {
            previousClosestPosition
        } else {
            closestPosition
        }

        closestChild?.let {
            listener.invoke(it)
        }

        return closestChild
    }

    /**
     * @param layoutManager layoutManager
     * @param velocityX     velocityX
     * @param velocityY     velocityY
     * @return snap後に左端に表示するitemのpositionを返却している模様
     */
    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager,
        velocityX: Int,
        velocityY: Int
    ): Int {
        val forwardDirection = velocityX > 0
        return if (forwardDirection) {
            previousClosestPosition + snapCount
        } else {
            previousClosestPosition - snapCount
        }
    }

    private fun getDistance(
        targetView: View?,
        helper: OrientationHelper
    ): Int {
        val childStart = getChildPosition(targetView, helper)
        val containerStart = getContainerPosition(helper)
        return childStart - containerStart
    }

    private fun getContainerPosition(
        helper: OrientationHelper
    ): Int {
        return helper.startAfterPadding
    }

    private fun getChildPosition(
        targetView: View?,
        helper: OrientationHelper
    ): Int {
        return helper.getDecoratedStart(targetView)
    }
}