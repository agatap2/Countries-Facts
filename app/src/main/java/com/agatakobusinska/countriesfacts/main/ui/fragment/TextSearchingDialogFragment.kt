package com.agatakobusinska.countriesfacts.main.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.agatakobusinska.countriesfacts.R
import com.agatakobusinska.countriesfacts.main.ui.activity.SearchingResultActivity
import com.agatakobusinska.countriesfacts.main.utils.SearchingDataType
import kotlinx.android.synthetic.main.fragment_searching_alert_dialog_text.view.*

class TextSearchingDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_searching_alert_dialog_text, null)

        val alertDialog = builder.setView(view).setTitle(
            arguments?.getString(
                TITLE
            ) ?: requireContext().resources.getString(R.string.searchingWarning)
        ).setPositiveButton(getString(R.string.search_button)) { _, _ -> }
            .setNegativeButton(getString(R.string.cancel_button)) { _, _ -> }
            .create()

        alertDialog.setOnShowListener {
            val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                if (view.searching_edit_text.text.isNullOrBlank().not()) {
                    val intent = Intent(context, SearchingResultActivity::class.java)
                    intent.putExtra(SEARCHING_RESULT, view.searching_edit_text.text.toString())
                    intent.putExtra(SEARCHING_TYPE, arguments?.getSerializable(SEARCHING_TYPE))
                    startActivity(intent)
                    dismiss()
                } else {
                    Toast.makeText(context, getString(R.string.searchingWarning), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        alertDialog.show()

        return alertDialog
    }

    companion object {

        private const val TITLE: String = "title"
        const val SEARCHING_TYPE: String = "type"
        const val SEARCHING_RESULT: String = "searching_result"

        fun getInstance(
            title: String, type: SearchingDataType
        ): TextSearchingDialogFragment {
            val fragment = TextSearchingDialogFragment()
            val bundle = Bundle()
            bundle.putString(TITLE, title)
            bundle.putSerializable(SEARCHING_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }
}