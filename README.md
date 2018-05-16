# dateTimePicker

### Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
### Add the dependency
  
      dependencies {
	        
		implementation 'com.github.ishwarverma39:dateTimePicker:v1.0.1'
     }

### How to Use
(See [SampleActivity](https://github.com/ishwarverma39/dateTimePicker/blob/master/app/src/main/java/com/livtech/MainActivity.java) for a more complete example)
    
First create a listener object:
    
    private DateTimeSetListener dateTimeSetListener = new DateTimeSetListener() {
        @Override
        public void onDateTimeSet(Date date) {
            Log.d("onDateTimeSet", "===" + date.toString());
        }
    };
    
Then pass the listener into the builder and show the dialog:
    
    DateTimePicker dateTimePicker =
                new DateTimePicker.Builder()
                        .setSelectedDate(Calendar.getInstance().getTime())
                        .setDateTimeSetListener(dateTimeSetListener)
                        .build();
        dateTimePicker.show(getSupportFragmentManager());

