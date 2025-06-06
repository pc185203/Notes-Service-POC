import './App.css';
import {BrowserRouter as Router, Route, Routes, Navigate, Outlet} from 'react-router-dom';
import {NoteProvider} from './Components/NoteContext';
import {UserNotes} from './Components/UserNotes';
import {AddNote} from './Components/AddNote';
import {NoteDetail} from './Components/NoteDetail';
import {LoginPage} from './Components/LoginPage';

function App() {
  return (
    <Router>
        <Routes>
            <Route path="/" element={<LoginPage/>} />
            <Route element={<ProtectedRoutes/>}>
                <Route element={ <NoteProvider> <Outlet/> </NoteProvider> }>
                    <Route path="/home" element={<UserNotes/>} />
                    <Route path="/add-note" element={<AddNote/>} />
                    <Route path="/note-detail/:id" element={<NoteDetail/>} />
                </Route>
            </Route>
        </Routes>
    </Router>
  );
}

const ProtectedRoutes = () => {
    const token = sessionStorage.getItem("token");
    return token? <Outlet/> : <Navigate to="/" replace />;      // outlet renders its children
};


export default App;
