import {useState} from 'react';
import {useNoteContext} from './NoteContext';
import {useNavigate} from 'react-router-dom';
import './AddNote.css';
import {addNote} from './ApiMethods';

export const AddNote = () => {
    const [newNote, setNewNote] = useState('');
    const navigate = useNavigate();
    const {getNotes} = useNoteContext();
    const [error, setError] = useState(false);

    const handleSave = async () => {
        const date = new Date();
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const seconds = String(date.getSeconds()).padStart(2, '0');

        const formattedDate = `${day}/${month}/${year}, ${hours}:${minutes}:${seconds}`;
        const n = {
            note: newNote,
            created: formattedDate,
            last_edited: formattedDate
        }
        const response = await addNote(n);
        if (response.status === 403 || response.status === 400) {
            document.getElementById("error").innerText = response.data;
            setError(true);
            setTimeout(() => setError(false),3000);
            return;
        }
        getNotes();
        navigate("/home",{replace: true});
    };


    return (
        <div className="container1">
        <div className="add-note-box">
        <p style={{fontSize: "25px"}} id="error" className={`error-message ${error?"show":""}`}>no error for now</p>
           <h1>Add Note</h1>
           <div className="textarea-container">
            <textarea
              rows="20"
              maxLength="4000"
              value={newNote}
              onChange={(e) => setNewNote(e.target.value)}
              placeholder="Type your note here..."
              ></textarea>
            <div className="char-count">
              Character count is: {newNote.length} / 4000
            </div>
           </div>
           <div className="btn-group">
               <button className="cancel-btn" onClick={() => navigate("/home",{replace: true})}><strong>Cancel</strong></button>
               <button className="save-btn" onClick={handleSave}><strong>Save</strong></button>
           </div>
        </div>
        </div>
    );
};