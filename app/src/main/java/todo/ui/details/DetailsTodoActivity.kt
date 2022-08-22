package todo.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import todo.model.Constant
import todo.ui.R
import todo.ui.databinding.ActivityDetailsTodoBinding
import todo.ui.home.MainActivity

@AndroidEntryPoint
class DetailsTodoActivity : BaseActivity<ActivityDetailsTodoBinding,DetailsViewModel>(),Navigator {
    // to sorted messages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.room = intent.getParcelableExtra(Constant.EXTRA_ROOM)!!
        viewDataBinding.vmDetails = viewModel
        viewModel.navigator = this
        viewModel.title.set(viewModel.room.name)
        viewModel.details.set(viewModel.room.details)
        viewModel.choiceData.set(viewModel.room.date.toString())
        // create viewModel
        viewModel= ViewModelProvider(this).get(DetailsViewModel::class.java)

    }

    override fun getLayoutID(): Int {
       return R.layout.activity_details_todo
    }

    override fun makeViewModelProvider(): DetailsViewModel {
       return ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }


}