# training-location-API
Uma API para localizar e avaliar locais para treinar lutas.

```mermaid
classDiagram
    class User {
        -name: String
        -password: String
        -perfil_img: String
    }
    
    class TrainingLocation {
        -location: String
        -name: String
        -fight_tag: String
        -description: String
        -photos: List~String~
        -evaluation_average: int
        -responsible: String
        -price: String
        +assessments: Assessment[]
        +days_activities: DayActivity[]
    }
    
    class DayActivity {
        -day: String
        +activities: Activity[]
    }
    
    class Activity {
        -name: String
        -start_time: String
        -end_time: String
    }

    class Assessment {
        -user: String
        -assessment: int
    }

    TrainingLocation "1" --> "many" DayActivity
    DayActivity "1" --> "many" Activity
    TrainingLocation "1" --> "many" Assessment
```



Cloudinary para upload de imagens.


BD_HOST=postgres.railway.internal
BD_PORT=5432
DB_NAME=railway
DB_USER=postgres
DB_PASSWORD=HAyUmdOHDORJOKCFHvdwOBVyEDKhFIdz
TOKEN_SECRET=banana

