# Android SegmentedControl + multi row support 
### minSdk API 14+

![N|Solid](https://raw.githubusercontent.com/RobertApikyan/SegmentedControl/release_v0.1/app/src/main/res/mipmap-hdpi/ic_launcher.png)

[Demo App, Play store link](https://play.google.com/store/apps/details?id=segmented_control.widget.custom.android.com.segmentedcontrolexample&hl=en)

[Or try demo App online !](https://appetize.io/app/y4e91xhxgp47956bf73da4z4yg)

[![](https://jitpack.io/v/RobertApikyan/SegmentedControl.svg)](https://jitpack.io/#RobertApikyan/SegmentedControl)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Segmented control for android, with a lot of customization properties

<img src="https://raw.githubusercontent.com/RobertApikyan/SegmentedControl/release_v0.1/docs/intro.gif.gif" width="300" height="525" />

## ScreenShots
<img src="https://raw.githubusercontent.com/RobertApikyan/SegmentedControl/release_v0.1/docs/device-2017-09-14-133621.png" width="400" />
<img src="https://raw.githubusercontent.com/RobertApikyan/SegmentedControl/release_v0.1/docs/device-2017-09-14-133711.png" width="400" />
<img src="https://raw.githubusercontent.com/RobertApikyan/SegmentedControl/release_v0.1/docs/device-2017-09-14-133736.png" width="400" />
<img src="https://raw.githubusercontent.com/RobertApikyan/SegmentedControl/release_v0.1/docs/device-2017-09-14-133907.png" width="400" />
<img src="https://raw.githubusercontent.com/RobertApikyan/SegmentedControl/release_v0.1/docs/device-2017-09-14-134003.png" width="400" />
<img src="https://raw.githubusercontent.com/RobertApikyan/SegmentedControl/release_v0.1/docs/device-2017-09-14-202249.png" width="400" />


## Download
### Gradle 
#### Add to project level build.gradle
```groovy
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
#### Add dependency to app module level build.gradle
```groovy
    dependencies {
	        implementation 'com.github.RobertApikyan:SegmentedControl:1.0.5'
	}
```
### Maven
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
#### Add dependency
```xml
	<dependency>
	    <groupId>com.github.RobertApikyan</groupId>
	    <artifactId>SegmentedControl</artifactId>
	    <version>1.0.5</version>
	</dependency>ø
```

 
### Done.

## Simple usage in XML
```xml
<segmented_control.widget.custom.android.com.segmentedcontrol.SegmentedControl
                android:id="@+id/segmented_control"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_margin="8dp"
                app:columnCount="3"
                app:distributeEvenly="true"
                app:textVerticalPadding="6dp"
                app:radius="12dp"
                app:segments="@array/your_array_data" />
```

# Attributes 
```xml
     <attr name="distributeEvenly" format="boolean" /> setDistributeEvenly(boolean)
     <attr name="columnCount" format="integer" /> setColumnCount(int)
     <attr name="segments" format="reference" /> addSegments(Object[]), addSegments(List)
     <attr name="selectedStrokeColor" format="color" /> setSelectedStrokeColor(int)
     <attr name="unSelectedStrokeColor" format="color" /> setUnSelectedStrokeColor(int)
     <attr name="strokeWidth" format="dimension" / setStrokeWidth(int)
     <attr name="selectedBackgroundColor" format="color" /> setSelectedBackgroundColor(int)
     <attr name="unSelectedBackgroundColor" format="color" /> setUnSelectedBackgroundColor(int)
     <attr name="selectedTextColor" format="color"/> setSelectedTextColor(int)
     <attr name="unSelectedTextColor" format="color"/> setUnSelectedTextColor(int)
     <attr name="textSize" format="dimension"/> setTextSize(int)
     <attr name="textHorizontalPadding" format="dimension"/> setTextHorizontalPadding(int)
     <attr name="textVerticalPadding" format="dimension"/> setTextVerticalPadding(int)
     <attr name="segmentVerticalMargin" format="dimension"/> setSegmentVerticalMargin(int)
     <attr name="segmentHorizontalMargin" format="dimension"/> setSegmentHorizontalMargin(int)
     <attr name="radius" format="dimension"/> setRadius(int)
     <attr name="topLeftRadius" format="dimension"/> setTopLeftRadius(int)
     <attr name="topRightRadius" format="dimension"/> setTopRightRadius(int)
     <attr name="bottomRightRadius" format="dimension"/> setBottomRightRadius(int)
     <attr name="bottomLeftRadius" format="dimension"/> setBottomLeftRadius(int)
     <attr name="radiusForEverySegment" format="boolean"/> setRadiusForEverySegment(boolean)
     <attr name="fontAssetPath" format="string"/>  setTypeFace(TypeFace)
```
        
### Note: After every configuration change call segmentedControl.notifyConfigIsChanged() method 
#### Example.
```java
        segmentedControl.setStrokeWidth(width.intValue());
        segmentedControl.setColumnCount(columnCount);
        segmentedControl.notifyConfigIsChanged();
```

> SegmentedControl uses SegmentAdapter and SegmentViewHolder for displaying segments. There are allready exist a default implementations for SegmentAdapter (SegmentAdapterImpl) and SegmentViewHolder (SegmentViewHolderImpl), but if you want to make your custom implementation... well here is the steps 
### 1. define segment_item.xml inside layouts folder
```xml
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TextView
            android:id="@+id/text_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="center"
            android:layout_margin="2dp"
            android:background="@color/colorPrimary"
            android:gravity="center"
            android:textColor="@color/colorAccent"
            android:textSize="14sp" />
    </LinearLayout>
```

### 2. Craete SegmentViewHolder instance AppSegmentViewHolder (here I define the segment generic data type as a String)
```java
    public class AppSegmentViewHolder extends SegmentViewHolder<String> {
        TextView textView;
    
        public AppSegmentViewHolder(@NonNull View sectionView) {
            super(sectionView);
            textView = (TextView) sectionView.findViewById(R.id.text_view);
        }

        @Override
        protected void onSegmentBind(String segmentData) {
            textView.setText(segmentData);
        }
    }
```
### 3. Create SegmentAdapter instance 
```java
    public class AppSegmentAdapter extends SegmentAdapter<String, AppSegmentViewHolder> {

        @NonNull
        @Override
        protected AppSegmentViewHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            return new AppSegmentViewHolder(layoutInflater.inflate(R.layout.item_segment, null));
        }
    }
```
### 4. Pass the adapter to the segmentedControl
```java
    segmentedControl = (SegmentedControl) findViewById(R.id.segmented_control);
    segmentedControl.setAdapter(new AppSegmentAdapter());
```
### 5.Finally add segements data. 
```java
    segmentedControl.addSegments(getResources().getStringArray(R.array.segments));
```
### Thatas it ) 
### For custom implementation use SegmentedControlUtils helper class in order to define segment background type and background radius.

[![View Robert Apikyan profile on LinkedIn](https://www.linkedin.com/img/webpromo/btn_viewmy_160x33.png)](https://www.linkedin.com/in/robert-apikyan-24b915130/)

License
-------

    Copyright 2017 Robert Apikyan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
