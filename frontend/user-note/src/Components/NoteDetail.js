import {useState} from 'react';
import './NoteDetail.css';
import {useLocation, useNavigate} from 'react-router-dom';
import {updateNote,deleteNoteById} from './ApiMethods';
import {useNoteContext} from './NoteContext';

export const NoteDetail = () => {
    const location = useLocation();
    const {note} = location.state || "no data";
    const [isEditing, setIsEditing] = useState(false);
    const [editedNote, setEditedNote] = useState(note);
    const [error, setError] = useState(false);
    const navigate = useNavigate();
    const {getNotes} = useNoteContext();

 const handleEditToggle = () => {
    setIsEditing(!isEditing);
 };

const handleNoteChange = (newNote) => {
    setEditedNote((prevNote) =>
        ({...prevNote, note: newNote})
    );
};

const handleDateChange = (newDate) => {
    setEditedNote((prevNote) =>
        ({...prevNote, last_edited: newDate})
    );
};

const update = async(updatedNote) => {
    const response = await updateNote(updatedNote);
    if (response.status === 403 || response.status === 400) {
        document.getElementById("error").innerText = response.data;
        setError(true);
        setTimeout(() => setError(false),3000);
        return false;
    }
    return true;
};

 const handleSave = async() => {
    const date = new Date();
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    const formattedDate = `${day}/${month}/${year}, ${hours}:${minutes}:${seconds}`;
    const updatedNote = {...editedNote, last_edited: formattedDate};
    const updated = await update(updatedNote);
    if(!updated)
    {
        setEditedNote(note);
        handleEditToggle();
        return;
    }
    handleEditToggle();
    handleDateChange(formattedDate);
 };

 const handleDelete = async(note) => {
     const response = await deleteNoteById(note.id);
     if (response.status === 403) {
         document.getElementById("error").innerText = response.data;
         setError(true);
         setTimeout(() => setError(false),3000);
         return;
     }
     getNotes();
     navigate('/home',{replace: true});
 };

 const handleBack = () => {
    getNotes();
    navigate('/home',{replace: true});
 };

    return (
    <div className="container2">
    <div className="note-detail">
        <p id="error" style={{fontSize: "23px"}} className={`error-message ${error?"show":""}`}>no error for now</p>
        <h1>User Note</h1>
        <h3>Created: <span className="date">{editedNote.created}</span></h3>
        <h3>Last edited: <span className="date">{editedNote.last_edited}</span></h3>
        {isEditing ? (
        <>
        <textarea rows="20" maxLength="4000" value={editedNote.note}
        onChange={(e) => handleNoteChange(e.target.value)} ></textarea>
        <div className="char-count">
          Character count is: {editedNote.note.length} / 4000
        </div>
        </>
         ) : ( <textarea rows="20" readOnly>{editedNote.note}</textarea> )}
        <div className="btn-group">
            <button className="back-btn" onClick={handleBack}><strong>Back</strong></button>
            <button className="delete-btn" onClick={() => handleDelete(editedNote)}><strong>Delete</strong></button>
            {isEditing ? ( <button className="save-btn" onClick={handleSave}><strong>Save</strong></button> ) :
            ( <button className="edit-btn" onClick={handleEditToggle}><strong>Edit</strong></button> )}
        </div>
    </div>
    </div>
    );
 };

 // if a function has a parameter then use arrow function or else it will execute on rendering itself which is not required