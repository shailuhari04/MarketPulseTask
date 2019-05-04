package com.marketpulsetask;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("criteria")
    @Expose
    private List<Criterium> criteria = null;
    public final static Parcelable.Creator<ResponseModel> CREATOR = new Creator<ResponseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ResponseModel createFromParcel(Parcel in) {
            return new ResponseModel(in);
        }

        public ResponseModel[] newArray(int size) {
            return (new ResponseModel[size]);
        }

    };

    protected ResponseModel(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.tag = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.criteria, (Criterium.class.getClassLoader()));
    }

    public ResponseModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Criterium> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterium> criteria) {
        this.criteria = criteria;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(tag);
        dest.writeValue(color);
        dest.writeList(criteria);
    }

    public int describeContents() {
        return 0;
    }


    public static class Criterium implements Parcelable {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("variable")
        @Expose
        private Variable variable;
        public final static Parcelable.Creator<Criterium> CREATOR = new Creator<Criterium>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Criterium createFromParcel(Parcel in) {
                return new Criterium(in);
            }

            public Criterium[] newArray(int size) {
                return (new Criterium[size]);
            }

        };

        protected Criterium(Parcel in) {
            this.type = ((String) in.readValue((String.class.getClassLoader())));
            this.text = ((String) in.readValue((String.class.getClassLoader())));
            this.variable = ((Variable) in.readValue((Variable.class.getClassLoader())));
        }

        public Criterium() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Variable getVariable() {
            return variable;
        }

        public void setVariable(Variable variable) {
            this.variable = variable;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(type);
            dest.writeValue(text);
            dest.writeValue(variable);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Variable implements Parcelable {

        @SerializedName("$1")
        @Expose
        private $1 $1;
        @SerializedName("$2")
        @Expose
        private $2 $2;
        @SerializedName("$3")
        @Expose
        private $3 $3;
        @SerializedName("$4")
        @Expose
        private $4 $4;

        public final static Parcelable.Creator<Variable> CREATOR = new Creator<Variable>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Variable createFromParcel(Parcel in) {
                return new Variable(in);
            }

            public Variable[] newArray(int size) {
                return (new Variable[size]);
            }

        };

        protected Variable(Parcel in) {
            this.$1 = (($1) in.readValue(($1.class.getClassLoader())));
            this.$2 = (($2) in.readValue(($2.class.getClassLoader())));
            this.$3 = (($3) in.readValue(($3.class.getClassLoader())));
            this.$4 = (($4) in.readValue(($4.class.getClassLoader())));
        }

        public Variable() {
        }

        public $1 get$1() {
            return $1;
        }

        public void set$1($1 $1) {
            this.$1 = $1;
        }

        public $2 get$2() {
            return $2;
        }

        public void set$2($2 $2) {
            this.$2 = $2;
        }

        public $3 get$3() {
            return $3;
        }

        public void set$3($3 $3) {
            this.$3 = $3;
        }

        public $4 get$4() {
            return $4;
        }

        public void set$4($4 $4) {
            this.$4 = $4;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue($1);
            dest.writeValue($2);
            dest.writeValue($3);
            dest.writeValue($4);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class $1 implements Parcelable {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("values")
        @Expose
        private List<Integer> values = null;
        public final static Parcelable.Creator<$1> CREATOR = new Creator<$1>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public $1 createFromParcel(Parcel in) {
                return new $1(in);
            }

            public $1[] newArray(int size) {
                return (new $1[size]);
            }

        };

        protected $1(Parcel in) {
            this.type = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.values, (java.lang.Integer.class.getClassLoader()));
        }

        public $1() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Integer> getValues() {
            return values;
        }

        public void setValues(List<Integer> values) {
            this.values = values;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(type);
            dest.writeList(values);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class $2 implements Parcelable {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("values")
        @Expose
        private List<Integer> values = null;
        public final static Parcelable.Creator<$2> CREATOR = new Creator<$2>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public $2 createFromParcel(Parcel in) {
                return new $2(in);
            }

            public $2[] newArray(int size) {
                return (new $2[size]);
            }

        };

        protected $2(Parcel in) {
            this.type = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.values, (java.lang.Integer.class.getClassLoader()));
        }

        public $2() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Integer> getValues() {
            return values;
        }

        public void setValues(List<Integer> values) {
            this.values = values;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(type);
            dest.writeList(values);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class $3 implements Parcelable {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("values")
        @Expose
        private List<Double> values = null;
        public final static Parcelable.Creator<$3> CREATOR = new Creator<$3>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public $3 createFromParcel(Parcel in) {
                return new $3(in);
            }

            public $3[] newArray(int size) {
                return (new $3[size]);
            }

        };

        protected $3(Parcel in) {
            this.type = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.values, (java.lang.Double.class.getClassLoader()));
        }

        public $3() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getValues() {
            return values;
        }

        public void setValues(List<Double> values) {
            this.values = values;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(type);
            dest.writeList(values);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class $4 implements Parcelable {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("study_type")
        @Expose
        private String studyType;
        @SerializedName("parameter_name")
        @Expose
        private String parameterName;
        @SerializedName("min_value")
        @Expose
        private Integer minValue;
        @SerializedName("max_value")
        @Expose
        private Integer maxValue;
        @SerializedName("default_value")
        @Expose
        private Integer defaultValue;
        public final static Parcelable.Creator<$4> CREATOR = new Creator<$4>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public $4 createFromParcel(Parcel in) {
                return new $4(in);
            }

            public $4[] newArray(int size) {
                return (new $4[size]);
            }

        };

        protected $4(Parcel in) {
            this.type = ((String) in.readValue((String.class.getClassLoader())));
            this.studyType = ((String) in.readValue((String.class.getClassLoader())));
            this.parameterName = ((String) in.readValue((String.class.getClassLoader())));
            this.minValue = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.maxValue = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.defaultValue = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public $4() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStudyType() {
            return studyType;
        }

        public void setStudyType(String studyType) {
            this.studyType = studyType;
        }

        public String getParameterName() {
            return parameterName;
        }

        public void setParameterName(String parameterName) {
            this.parameterName = parameterName;
        }

        public Integer getMinValue() {
            return minValue;
        }

        public void setMinValue(Integer minValue) {
            this.minValue = minValue;
        }

        public Integer getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Integer maxValue) {
            this.maxValue = maxValue;
        }

        public Integer getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Integer defaultValue) {
            this.defaultValue = defaultValue;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(type);
            dest.writeValue(studyType);
            dest.writeValue(parameterName);
            dest.writeValue(minValue);
            dest.writeValue(maxValue);
            dest.writeValue(defaultValue);
        }

        public int describeContents() {
            return 0;
        }

    }


}
