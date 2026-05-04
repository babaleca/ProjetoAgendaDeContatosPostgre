import { useEffect } from 'react'
import { Book } from '../types'

interface BookModalProps {
  book: Book
  recommendations: Book[]
  loading: boolean
  onClose: () => void
  onSelectBook: (book: Book) => void
}

export function BookModal({ book, recommendations, loading, onClose, onSelectBook }: BookModalProps) {
  useEffect(() => {
    const handleKey = (e: KeyboardEvent) => {
      if (e.key === 'Escape') onClose()
    }
    window.addEventListener('keydown', handleKey)
    document.body.style.overflow = 'hidden'
    return () => {
      window.removeEventListener('keydown', handleKey)
      document.body.style.overflow = ''
    }
  }, [onClose])

  const stars = Math.round(book.averageRating)

  return (
    <div className="modal-backdrop" onClick={onClose}>
      <div className="modal" onClick={(e) => e.stopPropagation()}>
        <button className="modal-close" onClick={onClose}>✕</button>

        <div className="modal-header">
          <div className="modal-cover">
            {book.thumbnail ? (
              <img src={book.thumbnail} alt={book.title} />
            ) : (
              <div className="modal-cover-placeholder">
                <span>{book.title.charAt(0)}</span>
              </div>
            )}
          </div>
          <div className="modal-meta">
            <div className="modal-categories">
              {book.categories.map((c) => (
                <span key={c} className="category-tag">{c}</span>
              ))}
            </div>
            <h2 className="modal-title">{book.title}</h2>
            <p className="modal-author">{book.authors.join(', ')}</p>

            <div className="modal-stats">
              {book.averageRating > 0 && (
                <div className="stat">
                  <span className="stat-label">Avaliação</span>
                  <div className="modal-stars">
                    {Array.from({ length: 5 }).map((_, i) => (
                      <span key={i} className={i < stars ? 'star filled' : 'star'}>★</span>
                    ))}
                    <span className="stat-value">{book.averageRating.toFixed(1)}</span>
                  </div>
                </div>
              )}
              {book.pageCount > 0 && (
                <div className="stat">
                  <span className="stat-label">Páginas</span>
                  <span className="stat-value">{book.pageCount}</span>
                </div>
              )}
              {book.publishedDate && (
                <div className="stat">
                  <span className="stat-label">Publicado</span>
                  <span className="stat-value">{book.publishedDate.slice(0, 4)}</span>
                </div>
              )}
            </div>

            <p className="modal-description">
              {book.description.length > 400
                ? book.description.slice(0, 400) + '...'
                : book.description}
            </p>

            {book.previewLink && (
              <a href={book.previewLink} target="_blank" rel="noopener noreferrer" className="preview-btn">
                Ver no Google Books →
              </a>
            )}
          </div>
        </div>

        <div className="modal-recommendations">
          <h3>Você também pode gostar</h3>
          {loading ? (
            <div className="rec-loading">
              {Array.from({ length: 4 }).map((_, i) => (
                <div key={i} className="rec-skeleton" />
              ))}
            </div>
          ) : (
            <div className="rec-grid">
              {recommendations.map((rec) => (
                <div key={rec.id} className="rec-card" onClick={() => onSelectBook(rec)}>
                  {rec.thumbnail ? (
                    <img src={rec.thumbnail} alt={rec.title} />
                  ) : (
                    <div className="rec-placeholder">{rec.title.charAt(0)}</div>
                  )}
                  <p className="rec-title">{rec.title}</p>
                  <p className="rec-author">{rec.authors[0]}</p>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  )
}
