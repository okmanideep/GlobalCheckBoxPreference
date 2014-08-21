package com.manidesto.globalcheckboxpreferenceview;

import com.manidesto.globalcheckboxprefdemo.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A CheckBox setting view which you can use anywhere(not just preference
 * fragment or activity)
 * 
 * @author manidesto
 * 
 */
public class GlobalCheckBoxPreference extends RelativeLayout {
	private RelativeLayout container;
	private CheckBox checkBox;
	private TextView nameTextView;
	private TextView summaryTextView;
	private SharedPreferences sharedPrefs;
	private String key;
	private String name;
	private String summary;
	private String summaryOn;
	private String summaryOff;
	private boolean defaultValue = false;
	private boolean useSharedPrefs = true;

	public GlobalCheckBoxPreference(Context context) {
		super(context);
		init(context);
	}

	public GlobalCheckBoxPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		applyAttributes(context, attrs);
		init(context);
	}

	public GlobalCheckBoxPreference(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		applyAttributes(context, attrs);
		init(context);
	}

	private void init(Context context) {
		View root = View.inflate(context, R.layout.global_check_box_preference,
				this);
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

		container = (RelativeLayout) root
				.findViewById(R.id.preference_container);
		checkBox = (CheckBox) root.findViewById(R.id.preference_check_box);

		nameTextView = (TextView) root.findViewById(R.id.preference_name);
		summaryTextView = (TextView) root.findViewById(R.id.preference_summary);
		updateViewElements();
	}

	private void applyAttributes(Context context, AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.GlobalCheckBoxPreference, 0, 0);

		try {
			key = a.getString(R.styleable.GlobalCheckBoxPreference_key);
			defaultValue = a.getBoolean(
					R.styleable.GlobalCheckBoxPreference_defaultValue, false);
			useSharedPrefs = a.getBoolean(
					R.styleable.GlobalCheckBoxPreference_useSharedPrefs, true);
			name = a.getString(R.styleable.GlobalCheckBoxPreference_name);
			summary = a.getString(R.styleable.GlobalCheckBoxPreference_summary);
			summaryOn = a
					.getString(R.styleable.GlobalCheckBoxPreference_summaryOn);
			summaryOff = a
					.getString(R.styleable.GlobalCheckBoxPreference_summaryOff);
		} finally {
			a.recycle();
		}
	}

	/**
	 * Key is the unique string identifier used to store the preference
	 * value(checkbox state) in shared preferences
	 * 
	 * @return key of the preference to be stored in shared preferences
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key for the preference. Its used to save the preference
	 * value(checkbox state) in shared preferences
	 * 
	 * @param key
	 *            unique string identifier for the preference in shared
	 *            preferences
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Name thats written beside the chechbox. Typically the title of the
	 * setting
	 * 
	 * @return name or title of the setting thats written beside the checkbox
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name or title of the setting written beside the checkbox
	 * 
	 * @param name
	 *            name or title of the setting thats written beside the checkbox
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Summary is the explanation behind the name of the setting
	 * 
	 * @return summary of the setting
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the summary of the setting, the explanation behind the name
	 * 
	 * @param summary
	 *            summary of the setting
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * SummaryOn is the text behind the name of the setting displayed when the
	 * checkbox is checked
	 * 
	 * @return Summary of the setting when the checkbox is checked
	 */
	public String getSummaryOn() {
		return summaryOn;
	}

	/**
	 * SummaryOn is the text behind the name of the setting displayed when the
	 * checkbox is checked
	 * 
	 * @param summaryOn
	 *            Summary of the setting when the checkbox is checked
	 */
	public void setSummaryOn(String summaryOn) {
		this.summaryOn = summaryOn;
	}

	/**
	 * SummaryOff is the text behind the name of the setting displayed when the
	 * checkbox is unchecked
	 * 
	 * @return Summary of the setting when the checkbox is unchecked
	 */
	public String getSummaryOff() {
		return summaryOff;
	}

	/**
	 * SummaryOff is the text behind the name of the setting displayed when the
	 * checkbox is unchecked
	 * 
	 * @param summaryOff
	 *            Summary of the setting when the checkbox is unchecked
	 */
	public void setSummaryOff(String summaryOff) {
		this.summaryOff = summaryOff;
	}

	/**
	 * 
	 * @return Default value of the checkbox(checked or unchecked). false if not
	 *         mentioned in attributes.
	 */
	public boolean getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Checks the checkbox if true or Unchecks the checkbox if false and stores
	 * the value in the shared preferences
	 * 
	 * @param value
	 *            state of the checkbox - checked = true, unchecked = false
	 */
	public void setChecked(boolean value) {
		setBooleanInPrefs(value);
	}

	/**
	 * SharedPreferences object that is used by this view to store the
	 * preference value(state of the checkbox).
	 * 
	 * PreferenceManager.getDefaultSharedPreferences(Context context) returns
	 * different SharedPreferences for contexts of different packages(not
	 * Application Package). So use this function to get the SharedPreferences
	 * used by this view
	 * 
	 * @return SharedPreferences used by this view to store the preference
	 *         value(state of the checkbox)
	 */
	public SharedPreferences getSharedPreferences() {
		return sharedPrefs;
	}

	/**
	 * PreferenceManager.getDefaultSharedPreferences(Context context) returns
	 * different SharedPreferences for contexts of different packages(not
	 * Application Package). 
	 * 
	 * If you are already using shared preferences in some other Context or
	 * using a file name, set this view to use the same shared preferences to
	 * access this preference value(checkbox state) through that sharedPrefs.
	 * 
	 * @param sharedPrefs SharedPreferences already been used by you
	 */
	public void setSharedPreferences(SharedPreferences sharedPrefs) {
		this.sharedPrefs = sharedPrefs;
	}

	/**
	 * Whether this view is using shared preferences to store the checkbox value
	 * to store the setting and restore the checkbox state when the app is
	 * closed and reopened
	 * 
	 * @return true if the setting is stored in shared preferences, false
	 *         otherwise
	 */
	public boolean usesSharedPrefs() {
		return useSharedPrefs;
	}

	/**
	 * Whether to use shared preferences to store the checkbox state
	 * 
	 * @param useSharedPrefs
	 *            true if the setting should be stored in shared preferences,
	 *            false otherwise
	 */
	public void UseSharedPrefs(boolean useSharedPrefs) {
		this.useSharedPrefs = useSharedPrefs;
	}

	/**
	 * A public function that sets the typeface provided to both title text view
	 * and summary text view
	 * 
	 * @param typeface
	 *            Typeface object of the custom font
	 */
	public void setCustomFonts(Typeface typeface) {
		nameTextView.setTypeface(typeface);
		summaryTextView.setTypeface(typeface);
	}

	/**
	 * updates the view elements to change the views in the layout to reflect
	 * the change in settings. Override this function to change the properties
	 * of the views you added when the settings change. Don't forget to call
	 * super.updateViewElements() when you do so
	 */
	protected void updateViewElements() {
		checkBox.setChecked(getBooleanFromPrefs(defaultValue));
		setBooleanInPrefs(checkBox.isChecked());

		nameTextView.setText(name);
		setSuitableSummary();
	}

	private boolean getBooleanFromPrefs(boolean defaultValue) {
		if (useSharedPrefs)
			return sharedPrefs.getBoolean(key, defaultValue);
		else
			return checkBox.isChecked();
	}

	private void setBooleanInPrefs(boolean value) {
		checkBox.setChecked(value);
		if (useSharedPrefs)
			sharedPrefs.edit().putBoolean(key, value).commit();
	}

	private void setSuitableSummary() {
		summaryTextView.setVisibility(View.VISIBLE);
		if (checkBox.isChecked()) {
			if (summaryOn != null) {
				summaryTextView.setText(summaryOn);
			} else if (summary != null) {
				summaryTextView.setText(summary);
			} else
				summaryTextView.setVisibility(View.GONE);
		} else {
			if (summaryOff != null)
				summaryTextView.setText(summaryOff);
			else if (summary != null)
				summaryTextView.setText(summary);
			else
				summaryTextView.setVisibility(View.GONE);
		}
	}

	private void togglePreference() {
		boolean currentPref = getBooleanFromPrefs(defaultValue);
		setBooleanInPrefs(!currentPref);
		updateViewElements();
	}

	/**
	 * Called on touch down. Override this function to run your custom animation
	 * for pressed state(For example, the ripple animation in Android L)
	 */
	protected void animatePressed() {
		container.setBackgroundColor(Color.rgb(232, 232, 232));
		checkBox.setBackgroundColor(Color.rgb(216, 216, 216));
	}

	/**
	 * Called when user lifts his finger up of slides his finger out of the view
	 * bounds. Override this function to run your custom animation during
	 * transition from pressed to normal state
	 */
	protected void animateNormal() {
		container.setBackgroundColor(Color.TRANSPARENT);
		checkBox.setBackgroundColor(Color.TRANSPARENT);
	}

	/**
	 * returns the current state of the check box
	 * 
	 * @return current state of the checkbox
	 */
	public boolean isChecked() {
		return checkBox.isChecked();
	}

	/**
	 * Use this function to get the checkbox element and apply custom styles or
	 * effects to it. If you override updateViewElements() or animatePressed()
	 * or animateNormal(), you might want to use this
	 * 
	 * @return CheckBox element of the view
	 */
	public CheckBox getCheckBox() {
		return checkBox;
	}

	/**
	 * Use this function to get the container view group element and apply
	 * custom styles or effects to it. If you override updateViewElements() or
	 * animatePressed() or animateNormal(), you might want to use this.
	 * 
	 * @return RelativeLayout that contains all the views inside
	 */
	public RelativeLayout getContainer() {
		return container;
	}

	/**
	 * nameTextView is the TextView that displays the title or name of the
	 * preference
	 * 
	 * @return TextView that displays the name or title of the preference
	 */
	public TextView getNameTextView() {
		return nameTextView;
	}

	/**
	 * summaryTextView is the TextView that displays the
	 * summary(,summaryOn,summaryOff) of the preference
	 * 
	 * @return TextView that displays the summary of the preference
	 */
	public TextView getSummaryTextView() {
		return summaryTextView;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}

	Rect rect;
	boolean ignore = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getActionMasked();
		if (ignore && action != MotionEvent.ACTION_UP)
			return false;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			rect = new Rect(getLeft(), getTop(), getRight(), getBottom());
			animatePressed();
			break;
		case MotionEvent.ACTION_OUTSIDE:
		case MotionEvent.ACTION_CANCEL:
			animateNormal();
			break;
		case MotionEvent.ACTION_MOVE:
			if (!rect.contains(rect.left + (int) event.getX(), rect.top
					+ (int) event.getY())) {
				animateNormal();
				ignore = true;
				return false;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (!ignore) {
				performClick();
				togglePreference();
				animateNormal();
			} else
				ignore = false;
			break;
		}
		return true;
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}

}
