package com.afoxplus.yalistoadmin.ui.screens.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.afoxplus.uikitcompose.ui.components.BottomSheetComponent
import com.afoxplus.uikitcompose.ui.components.ButtonOutlineYaListoComponent
import com.afoxplus.uikitcompose.ui.components.ButtonYaListoComponent
import com.afoxplus.uikitcompose.ui.components.ToolbarComponent
import com.afoxplus.uikitcompose.ui.theme.Dark05
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Light01
import com.afoxplus.uikitcompose.ui.theme.Light04
import com.afoxplus.yalistoadmin.R
import com.afoxplus.yalistoadmin.ui.screens.status.components.OrderShareView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderStatusScreen(
    orderViewModel: OrderViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val order = orderViewModel.orderState.collectAsState().value ?: return
    var orderShareView: MutableState<OrderShareView>?
    val context = LocalContext.current

    val statesOrder = orderViewModel.states.collectAsState().value
    val stateSelected = orderViewModel.stateSelected.collectAsState().value
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Light04)
    ) {
        val (toolbar, contentBox, footer) = createRefs()

        ToolbarComponent(
            modifier = Modifier
                .background(color = Light01)
                .constrainAs(toolbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            title = "Client",
            description = order.client.name
        ) {
            navigateBack()
        }
        orderShareView = remember { mutableStateOf(OrderShareView(order, context)) }
        AndroidView(
            modifier = Modifier
                .constrainAs(contentBox) {
                    top.linkTo(toolbar.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(footer.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            factory = {
                OrderShareView(order, it).apply {
                    post {
                        orderShareView?.value = this
                    }
                }
            }
        )

        ConstraintLayout(
            modifier = Modifier
                .background(Light01)
                .constrainAs(footer) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            val (textState, buttonUpdate, buttonPrint) = createRefs()
            Text(
                modifier = Modifier
                    .height(35.dp)
                    .background(Dark05)
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .constrainAs(textState) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                text = order.state,
                textAlign = TextAlign.Center,
                color = Light01,
                style = Header05SemiBold
            )

            ButtonYaListoComponent(
                modifier = Modifier.constrainAs(buttonUpdate) {
                    top.linkTo(textState.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = stringResource(id = R.string.order_update_state),
                onClick = {
                    isSheetOpen = true
                }
            )

            ButtonOutlineYaListoComponent(
                modifier = Modifier.constrainAs(buttonPrint) {
                    top.linkTo(buttonUpdate.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                text = stringResource(id = R.string.order_print)
            ) {
                orderShareView?.value?.capture(orderShareView?.value as OrderShareView)
            }

            if (isSheetOpen) {
                BottomSheetComponent(
                    title = stringResource(id = R.string.order_states_list),
                    list = statesOrder,
                    description = {
                        it.name
                    },
                    showIcon = {
                        it.isCheck
                    },
                    onClick = {
                        isSheetOpen = false
                        orderViewModel.updateCheckState(it.name)
                        orderViewModel.sendOrderState(it.name)
                    },
                    onDismiss = {
                        isSheetOpen = false
                    },
                    sheetState = sheetState
                )
            }
        }
    }
}
