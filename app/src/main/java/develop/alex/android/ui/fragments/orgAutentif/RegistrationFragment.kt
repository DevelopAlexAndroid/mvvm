package develop.alex.android.ui.fragments.orgAutentif

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import develop.alex.android.R
import kotlinx.android.synthetic.main.fragment_registration_org.*


class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_org, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ed_phone.setOnClickListener {
            button.isEnabled = !button.isEnabled
        }
//покраска
        val length = resources.getString(R.string.data_fot_you).length
        val word: Spannable = SpannableString(resources.getString(R.string.data_fot_you) + " 896152592093")
        word.setSpan(
            ForegroundColorSpan(Color.BLUE),
            length,
            word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = word

        //  edList()
    }

    fun edList() {
        ed_phone.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val text = ed_phone.text.toString()
                val textLength = ed_phone.text?.length

                if (text.endsWith("-") || text.endsWith(" ")) {
                    return
                }

                if (textLength == 1) {
                    if (!text.contains("(")) {
                        setText(StringBuilder(text).insert(text.length - 1, "(").toString())
                    }
                } else if (textLength == 5) {
                    if (!text.contains(")")) {
                        setText(StringBuilder(text).insert(text.length - 1, ")").toString())
                    }
                } else if (textLength == 6) {
                    setText(StringBuilder(text).insert(text.length - 1, " ").toString())
                } else if (textLength == 10) {
                    if (!text.contains("-")) {
                        setText(StringBuilder(text).insert(text.length - 1, "-").toString())
                    }
                } else if (textLength == 15) {
                    if (text.contains("-")) {
                        setText(StringBuilder(text).insert(text.length - 1, " ext").toString())
                    }
                }
            }
            private fun setText(text: String) {
                ed_phone.removeTextChangedListener(this)
                ed_phone.editableText.replace(0, ed_phone.text!!.length, text)
                ed_phone.setSelection(text.length)
                ed_phone.addTextChangedListener(this)
            }
        })
    }
}