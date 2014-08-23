GlobalCheckBoxPreference
========================

Android's checkbox preference that is customisable and can be used any where like a normal view

Normal State
![GlobalCheckBoxPreference#Unchecked](https://un7o1q.by3301.livefilestore.com/y2pxmvKMYH7kpq6Jpa8fvr98Cmbjf21yPCVNyQPLsZ0G16rZ3Sh3RTOfT_GG95PgzbcHOlv47Q2vLJ_yWeJDXon9-1yWCfe8YvmkV5lbVKpmJk/Screenshot_2014-08-21-02-40-47_1.jpg?psid=1 "GlobalCheckBoxPreference_Unchecked")

Pressed State
![GlobalCheckBoxPreference#Pressed](https://pcozwa.by3301.livefilestore.com/y2pK9-jvIln_lHP1DQ7F40uFPPrTylg2t3fkZd9w4eyXFibcH_ICUPMX4hyg4453v-KEztL-nBCv-nqQlJwWSwhWDDpOFtbAuJZ_XA8PMPi9mQ/Screenshot_2014-08-21-02-40-53_1.jpg?psid=1 "GlobalCheckBoxPreference_Pressed")

##Using GlobalCheckBoxPreference 

###Imports and code changes
* Import the following files to your project(copy+paste)
  * "GlobalCheckBoxPreference.java" to your project package
  * "gobal_check_box_preference.xml" to res/layout/

* Create 'res/values/attrs.xml' and paste the following code
  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <resources>
      <declare-styleable name="GlobalCheckBoxPreference">
          <attr name="name" format="string" />
          <attr name="summary" format="string" />
          <attr name="summaryOn" format="string" />
          <attr name="summaryOff" format="string" />
          <attr name="defaultValue" format="boolean" />
          <attr name="useSharedPrefs" format="boolean" />
          <attr name="key" format="string" />
          <attr name="iconSrc" format="integer" />
      </declare-styleable>
  </resources>
  ```
* Change the following line in GlobalCheckBoxPreference.java(line 3)

  ```java
  import com.manidesto.globalcheckboxprefdemo.R;
  ``` 
  to
  ```java
  import YOUR_APPLICATION_PACKAGE_NAME.R;
  //YOUR_APPLICATION_PACKAGE_NAME is the one you write in AndroidManifest.xml
  ```
###Understanding attributes
* **name** : Name of the setting or preference
* **summary** : Summary of the setting which is below the name
* **summaryOn** : Summary of the setting when the checkbox is checked
* **summaryOff** : Summary of the setting when the checkbox is unchecked
* **defaultValue** : Default state of the chechbox(checked or unchecked)
* **key** : Unique key of the preference to store the checkbox state and restore it when the app is restarted. When this key is not set(either through xml or java) the state of the checkbox or the preference value is not stored in the SharedPreferences and therefore not restored when the app is closed and reopened. 
* **iconSrc** : Provide a drawable resource Id to set an icon on the left of the title

###Using the view
* In the layout file that you want to add the GlobalChechBoxPreference view
  * add
  ```xml
  xmlns:custom="http://schemas.android.com/apk/res/YOUR_APPLICATION_PACKAGE_NAME"
  <!-- YOUR_APPLICATION_PACKAGE_NAME  is the one you write in AndroidManifest.xml -->
  ``` 
  after
  ```xml
  xmlns:android="http://schemas.android.com/apk/res/android"
  ```
  
  * use the view
  ```xml
  <PACKAGE_NAME.GlobalCheckBoxPreference
        custom:name="@string/preference_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        custom:defaultValue="true"
        custom:summaryOff="@string/preference_summary_off"
        custom:summaryOn="@string/preference_summary_on"
        custom:useSharedPrefs="false" 
        custom:iconSrc="@drawable/icon" />
  <!-- PACKAGE_NAME is package name of the package which contains GlobalCheckBoxPreference.java -->
  ```



