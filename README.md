
## 專案說明
APP 讀取 Local data，顯示課程資訊列表

#### 架構
- MVVM
- Coroutines
- Navigation component
專案使用 Navigation component，並使用 BottomNavigationView，保留日後 UI 設計的彈性。因目前設計只有課程主頁，故隱藏下方的 BottomNavigationView
- Room
- Glide

#### 專案資料夾
- database: Database 相關程式碼。Database 使用 Room Library 實作
- domain: UI 相關的資料類別
- network: 網路服務。模擬 API 獲取 Local data
- repository: 提供 Model 使用網路或是資料庫，獲取資料
- util: 常用的 Utility
- view

#### 資料
- HahowApi.kt: 設定 FakeService，模擬 API response
- CourseRepository.kt: 提供 Model 獲取資料的介面，目前資料獲取的方式，透過呼叫 getCourseByAPI() 取得
